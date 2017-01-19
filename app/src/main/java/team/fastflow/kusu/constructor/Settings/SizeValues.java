package team.fastflow.kusu.constructor.Settings;

import android.content.res.TypedArray;

import team.fastflow.kusu.R;

/**
 * Created by KuSu on 01.01.2017.
 */

public class SizeValues {
	private static int defBlock = 10;
	private static float defBlockFactor = 1.5f;
	private static float defDivisionFactor = 0.2f;
	private static float defDivisionPaddingFactor = 3 / 8.0f;
	private static float defPaddingFactor = 3;
	private static float defCheckSize = 0.8f;

	private static float defMovableDividerFactor = 3;
	private static boolean defGroupMovables = true;

	private static boolean defPercentHeight = true;
	private static float defPercent = 0.5f;
	private static int defHeight = 600;
	private static float defTextPercent = 0.6f;

	private int block = defBlock;
	private float percent = defPercent;
	private int height = defHeight;
	private float paddingFactor = defPaddingFactor;
	private float movableDividerFactor = defMovableDividerFactor;
	private boolean groupMovables = defGroupMovables;
	private boolean percentHeight = defPercentHeight;
	private float textPercent = defTextPercent;
	private float blockFactor = defBlockFactor;
	private float divisionFactor = defDivisionFactor;
	private float divisionPaddingFactor = defDivisionPaddingFactor;

	private float checkSize = defCheckSize;

	private String bigString = "A";

	public SizeValues(TypedArray attrs) {
		if (attrs == null)
			return;
		block = attrs.getDimensionPixelSize(R.styleable.fs_block_size, defBlock);
		percent = attrs.getFloat(R.styleable.fs_percent, defPercent);
		height = attrs.getDimensionPixelSize(R.styleable.fs_height, defHeight);
		paddingFactor = attrs.getFloat(R.styleable.fs_padding_factor, defPaddingFactor);
		movableDividerFactor = attrs.getFloat(R.styleable.fs_movable_divider_factor, defMovableDividerFactor);
		groupMovables = attrs.getBoolean(R.styleable.fs_group_movables, defGroupMovables);
		percentHeight = attrs.getBoolean(R.styleable.fs_percent_height, defPercentHeight);
		textPercent = attrs.getFloat(R.styleable.fs_text_percent, defTextPercent);
		blockFactor = attrs.getFloat(R.styleable.fs_block_factor, defBlockFactor);
		divisionFactor = attrs.getFloat(R.styleable.fs_division_factor, defDivisionFactor);
		divisionPaddingFactor = attrs.getFloat(R.styleable.fs_division_padding_factor, defDivisionPaddingFactor);
		checkSize = attrs.getFloat(R.styleable.fs_check_size, defCheckSize);
	}

	public int getFormulHeight(int h) {
		if (percentHeight) {
			return (int) (h * percent);
		} else
			return (int) Math.min(h, height);
	}

	public float getDivisionPaddingFactor() {
		return divisionPaddingFactor;
	}

	public void setDivisionPaddingFactor(float divisionPaddingFactor) {
		this.divisionPaddingFactor = divisionPaddingFactor;
	}

	public int getDivision() {
		return (int) (block * divisionFactor);
	}

	public int getwBlock() {
		return (int) (block * blockFactor);
	}

	public boolean isPercentHeight() {
		return percentHeight;
	}

	public void setPercentHeight(boolean percentHeight) {
		this.percentHeight = percentHeight;
	}

	public int getPadding() {
		return (int) (block * paddingFactor);
	}

	public int getMovableDivider() {
		return (int) (block * movableDividerFactor);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public float getTextPercent() {
		return textPercent;
	}

	public void setTextPercent(float textPercent) {
		this.textPercent = textPercent;
	}

	public float getBlockFactor() {
		return blockFactor;
	}

	public void setBlockFactor(float blockFactor) {
		this.blockFactor = blockFactor;
	}

	public float getDivisionFactor() {
		return divisionFactor;
	}

	public void setDivisionFactor(float divisionFactor) {
		this.divisionFactor = divisionFactor;
	}

	public float getPaddingFactor() {
		return paddingFactor;
	}

	public void setPaddingFactor(float paddingFactor) {
		this.paddingFactor = paddingFactor;
	}

	public float getMovableDividerFactor() {
		return movableDividerFactor;
	}

	public void setMovableDividerFactor(float movableDividerFactor) {
		this.movableDividerFactor = movableDividerFactor;
	}

	public boolean isGroupMovables() {
		return groupMovables;
	}

	public void setGroupMovables(boolean groupMovables) {
		this.groupMovables = groupMovables;
	}

	public String getBigString() {
		return bigString;
	}

	public void setBigString(String bigString) {
		this.bigString = bigString;
	}

	public float getCheckSize() {
		return checkSize;
	}

	public void setCheckSize(float checkSize) {
		this.checkSize = checkSize;
	}
}
