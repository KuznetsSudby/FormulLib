package com.kusu.constructor.Parser;

import android.content.Context;

import com.kusu.constructor.LeafType.Changeable;
import com.kusu.constructor.LeafType.Division;
import com.kusu.constructor.LeafType.Nextable;
import com.kusu.constructor.LeafType.Power;
import com.kusu.constructor.Prototype.Leaf;
import com.kusu.constructor.R;

/**
 * Created by KuSu on 18.01.2017.
 */

public class Parser {

    private static final int BAD_INDEX = -1;

    private char BLOCK_DEF = '@';
    private char CHANGEABLE_DEF = '&';
    private boolean seeMultiply = true;
    private boolean seeBrackets = false;
    private boolean seeSquareBrackets = false;

    public static final String def =
            "&A* ( ( &B^ ( 2*(&G / U))&+(&L/&P))/(&F^H)) -&K ^E+@&log (23/32)@";

    public static final String defRes =
            "&A*(/(&B^(2*(/(&G)(U)))&+/(&L)(&P))(&F^H))-&K^E+@&log (23/32)@";
    //    "&A*(/(&B^(2*(/(&G)(U)))&+/(&L)(&P))(&F^H))-&K^E+@&log (23/32)@";


    public Parser() {
    }

    public Parser(boolean seeMultiply, boolean seeBrackets, boolean seeSquareBrackets) {
        this.seeMultiply = seeMultiply;
        this.seeBrackets = seeBrackets;
        this.seeSquareBrackets = seeSquareBrackets;
    }

    public Parser(boolean seeSquareBrackets, boolean seeBrackets, boolean seeMultiply, char CHANGEABLE_DEF, char BLOCK_DEF) {
        this.seeSquareBrackets = seeSquareBrackets;
        this.seeBrackets = seeBrackets;
        this.seeMultiply = seeMultiply;
        this.CHANGEABLE_DEF = CHANGEABLE_DEF;
        this.BLOCK_DEF = BLOCK_DEF;
    }

    private String validateBaskets(Context context, String value) throws Exception {
        boolean block = false;
        boolean square = false;
        char c;
        int brackets = 0;
        int squareBrackets = 0;
        for (int i = 0; i < value.length(); i++) {
            c = value.charAt(i);
            if (c == BLOCK_DEF) {
                block = !block;
            }
            if (!block) {
                switch (c) {
                    case '(':
                        brackets++;
                        if (squareBrackets == 0)
                            square = false;
                        else if (square)
                            throw new Exception(context.getString(R.string.exception_brackets));
                        break;
                    case ')':
                        brackets--;
                        if (brackets < 0)
                            throw new Exception(context.getString(R.string.exception_brackets));
                        break;
                    case '[':
                        squareBrackets++;
                        if (brackets == 0)
                            square = true;
                        else if (!square)
                            throw new Exception(context.getString(R.string.exception_brackets));
                        break;
                    case ']':
                        squareBrackets--;
                        if (squareBrackets < 0)
                            throw new Exception(context.getString(R.string.exception_brackets));
                        break;
                }
            }
        }
        return value;
    }

    private String deleteSpace(String value) {
        boolean block = false;
        char c;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            c = value.charAt(i);
            if (c == BLOCK_DEF) {
                block = !block;
            }
            if (block) {
                builder.append(c);
            } else {
                if (c != ' ')
                    builder.append(c);
            }
        }
        return builder.toString();
    }

    public Leaf parseRoot(Context context, String text) throws Exception {
        validateBaskets(context, text);
        return parseString(
                context,
                convertDivision(deleteSpace(text)),
                null);
    }

    private String convertDivision(String value) {
        int index = 0;
        int indStart = 0;
        String temp;
        index = value.indexOf('/', index);
        while (index != -1) {
            switch (value.charAt(index - 1)) {
                case ')':
                    indStart = findPrevios(value, index - 1, '(', ')');
                    break;
                case ']':
                    indStart = findPrevios(value, index - 1, '[', ']');
                    break;
                default:
                    if (value.charAt(index - 1) == BLOCK_DEF) {
                        indStart = findPrevios(value, index - 2, true);
                    } else {
                        indStart = findPrevios(value, index - 1, false);
                    }
            }
            temp = value.substring(0, indStart)
                    + '/'
                    + value.substring(indStart, index)
                    + value.substring(index + 1);
            value = temp;
            index = value.indexOf('/', index);
        }
        return value;
    }

    private int findPrevios(String value, int index, boolean block) {
        char c;
        for (int i = index; i >= 0; i--) {
            c = value.charAt(i);
            switch (c) {
                case ')':
                case ']':
                case '*':
                case '-':
                case '+':
                case '/':
                case '^':
                    return i + 1;
                default:
                    if (c == BLOCK_DEF) {
                        if (block)
                            return i;
                        else
                            return i + 1;
                    }
            }
        }
        return -1;
    }

    private int findPrevios(String value, int index, char start, char end) {
        char c;
        int count = 0;
        for (int i = index; i >= 0; i--) {
            c = value.charAt(i);
            if (c == start) {
                count--;
                if (count == 0)
                    return i;
            } else if (c == end) {
                count++;
            }
        }
        return -1;
    }

    private Leaf parseString(Context context, String value, Leaf end) throws Exception {
        Leaf leaf;
        int index;
        String text;
        switch (value.charAt(0)) {
            case '+':
            case '-':
            case '=':
                leaf = new Nextable(value.charAt(0) + "");
                if (value.length() > 1)
                    leaf.list.add(parseString(
                            context,
                            value.substring(1),
                            end
                    ));
                else
                    throw new Exception(context.getString(R.string.exception_incorrect_value, value.charAt(0)));
                return leaf;
            case '*':
                if (!seeMultiply) {
                    if (value.length() > 1)
                        return parseString(
                                context,
                                value.substring(1),
                                end
                        );
                    else
                        throw new Exception(context.getString(R.string.exception_incorrect_value, value.charAt(0)));
                } else {
                    leaf = new Nextable(value.charAt(0) + "");
                    if (value.length() > 1)
                        leaf.list.add(parseString(
                                context,
                                value.substring(1),
                                end
                        ));
                    else
                        throw new Exception(context.getString(R.string.exception_incorrect_value, value.charAt(0)));
                    return leaf;
                }
            case '/':
                leaf = new Division("/");
                text = value.substring(1);
                if (text.isEmpty()) {
                    throw new Exception(context.getString(R.string.exception_incorrect_value, value));
                } else {
                    for (int k = 0; k < 2; k++) {
                        switch (text.charAt(0)) {
                            case '[':
                                index = findEndBasket(text, '[', ']') + 1;
                                break;
                            case '(':
                                index = findEndBasket(text, '(', ')') + 1;
                                break;
                            default:
                                index = findEndBlock(text);
                                break;
                        }
                        if (index == BAD_INDEX) {
                            throw new Exception(context.getString(R.string.exception_incorrect_value, value));
                        } else {
                            leaf.list.add(parseString(
                                    context,
                                    text.substring(0, index),
                                    null
                            ));
                        }
                        text = text.substring(index);
                    }
                    if (text.isEmpty()) {
                        if (end != null)
                            leaf.list.add(end);
                    } else {
                        leaf.list.add(parseString(
                                context,
                                text,
                                end
                        ));
                    }
                    return leaf;
                }
            case '^':
                leaf = new Power("^");
                text = value.substring(1);
                if (text.isEmpty()) {
                    throw new Exception(context.getString(R.string.exception_incorrect_value, value));
                } else {
                    switch (text.charAt(0)) {
                        case '[':
                            index = findEndBasket(text, '[', ']') + 1;
                            break;
                        case '(':
                            index = findEndBasket(text, '(', ')') + 1;
                            break;
                        default:
                            index = findEndBlock(text);
                            break;
                    }
                    if (index == BAD_INDEX) {
                        throw new Exception(context.getString(R.string.exception_incorrect_value, value));
                    } else {
                        leaf.list.add(parseString(
                                context,
                                text.substring(0, index),
                                null
                        ));
                    }
                    text = text.substring(index);
                    if (text.isEmpty()) {
                        if (end != null)
                            leaf.list.add(end);
                    } else {
                        leaf.list.add(parseString(
                                context,
                                text,
                                end
                        ));
                    }
                    return leaf;
                }
            case '(':
                return basketValue(context, value, end, '(', ')', seeBrackets);
            case '[':
                return basketValue(context, value, end, '[', ']', seeSquareBrackets);
            default:
                if (value.charAt(0) == BLOCK_DEF) {
                    index = value.indexOf(BLOCK_DEF, 1);
                    if (index == BAD_INDEX) {
                        throw new Exception(context.getString(R.string.exception_incorrect_value, value));
                    } else {
                        text = value.substring(0, index + 1);
                        leaf = convertToLeaf(text.substring(1, text.length() - 1));
                        text = value.substring(index + 1);
                        if (!text.isEmpty())
                            leaf.list.add(parseString(
                                    context,
                                    text,
                                    end
                            ));
                        else if (end != null)
                            leaf.list.add(end);
                        return leaf;
                    }
                } else {
                    index = findEndBlock(value);
                    text = value.substring(0, index);
                    leaf = convertToLeaf(text);
                    text = value.substring(index);
                    if (!text.isEmpty())
                        leaf.list.add(parseString(
                                context,
                                text,
                                end
                        ));
                    else if (end != null)
                        leaf.list.add(end);
                    return leaf;
                }
        }
    }

    private Leaf basketValue(Context context, String value,
                             Leaf end, char startChar, char endChar, boolean see) throws Exception {
        Leaf leaf;
        int index = findEndBasket(value, startChar, endChar);
        if (index == BAD_INDEX) {
            throw new Exception(context.getString(R.string.exception_incorrect_value, value));
        } else {
            String textCenter = value.substring(1, index);
            String textEnd = value.substring(index + 1);

            Leaf nd = null;
            if (see)
                nd = new Nextable(endChar + "");
            if (!textEnd.isEmpty())
                if (see) {
                    nd.list.add(parseString(
                            context,
                            textEnd,
                            end
                    ));
                } else {
                    nd = parseString(
                            context,
                            textEnd,
                            end
                    );
                }

            Leaf leafCenter = parseString(context, textCenter, nd);

            if (see) {
                Leaf st = new Nextable(startChar + "");
                st.list.add(leafCenter);
                leaf = st;
            } else {
                leaf = leafCenter;
            }
            return leaf;
        }
    }

    private int findEndBlock(String value) {
        for (int i = 1; i < value.length(); i++) {
            if (isEndable(value.charAt(i)))
                return i;
        }
        return value.length();
    }

    private boolean isEndable(char c) {
        switch (c) {
            case '(':
            case ')':
            case ']':
            case '[':
            case '*':
            case '-':
            case '+':
            case '/':
            case '^':
            default:
                if (c == BLOCK_DEF)
                    return true;
                else if (c == CHANGEABLE_DEF)
                    return true;
        }
        return false;
    }

    private int findEndBasket(String value, final char startChar, char endChar) {
        int depth = 1;
        boolean kvadr = false;
        for (int i = 1; i < value.length(); i++) {
            if (value.charAt(i) == startChar) {
                if (!kvadr)
                    depth++;
            } else if (value.charAt(i) == endChar) {
                if (!kvadr) {
                    depth--;
                    if (depth == 0)
                        return i;
                }
            } else if (value.charAt(i) == BLOCK_DEF) {
                kvadr = !kvadr;
            }
        }
        return BAD_INDEX;
    }

    private Leaf convertToLeaf(String text) {
        if (text.contains(CHANGEABLE_DEF + "")) {
            return new Changeable(text.substring(1));
        } else {
            return new Nextable(text.substring(0));
        }
    }
}
