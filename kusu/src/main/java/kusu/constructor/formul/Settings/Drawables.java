package kusu.constructor.formul.Settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import kusu.constructor.formul.R;

/**
 * Created by mikhail.chvarkou on 1/4/2017.
 */

public class Drawables {
	private static @DrawableRes int defIDDefaultBlock = R.drawable.bg_default;
	private static @DrawableRes int defIDMovableBlock = R.drawable.bg_movable;
	private static @DrawableRes int defIDChangeableBlock = R.drawable.bg_changeable;
	private static @DrawableRes int defIDGoodBlock = R.drawable.bg_good;
	private static @DrawableRes int defIDBadBlock = R.drawable.bg_bad;
	private static @DrawableRes int defIDUnselectedBlock = R.drawable.bg_unselected;
	private static @DrawableRes int defIDCheckBad = R.drawable.ic_check_circle_bad;
	private static @DrawableRes int defIDCheckGood = R.drawable.ic_check_circle_good;

	private static @DrawableRes int defIDPlusBlock = R.drawable.ic_plus;
	private static @DrawableRes int defIDMinusBlock = R.drawable.ic_minus;
	private static @DrawableRes int defIDMultiplyBlock = R.drawable.ic_mult;
	private static @DrawableRes int defIDDivisionBlock = R.drawable.ic_division;
	private static @DrawableRes int defIDEquallyBlock = R.drawable.ic_equally;
	private static @DrawableRes int defIDBktLeftBlock = R.drawable.ic_bkt_left;
	private static @DrawableRes int defIDBktRightBlock = R.drawable.ic_bkt_right;

	private Drawable defaultBlock;
	private Drawable movableBlock;
	private Drawable changeableBlock;
	private Drawable goodBlock;
	private Drawable badBlock;
	private Drawable unselectedBlock;
	private Drawable checkGood;
	private Drawable checkBad;

	private Drawable plusBlock;
	private Drawable minusBlock;
	private Drawable equallyBlock;
	private Drawable bktLeftBlock;
	private Drawable bktRightBlock;
	private Drawable multiplyBlock;
	private Drawable divisionBlock;

	public Drawables(TypedArray attrs) {
		if (attrs == null)
			return;
		defaultBlock = attrs.getDrawable(R.styleable.fs_default_block);
		badBlock = attrs.getDrawable(R.styleable.fs_bad_block);
		goodBlock = attrs.getDrawable(R.styleable.fs_good_block);
		movableBlock = attrs.getDrawable(R.styleable.fs_movable_block);
		changeableBlock = attrs.getDrawable(R.styleable.fs_changeable_block);
		unselectedBlock = attrs.getDrawable(R.styleable.fs_unselected_block);

		checkGood  = attrs.getDrawable(R.styleable.fs_check_good);
		checkBad  = attrs.getDrawable(R.styleable.fs_check_bad);
		plusBlock  = attrs.getDrawable(R.styleable.fs_plus_block);
		minusBlock  = attrs.getDrawable(R.styleable.fs_minus_block);
		equallyBlock  = attrs.getDrawable(R.styleable.fs_equally_block);
		bktLeftBlock  = attrs.getDrawable(R.styleable.fs_bkt_left_block);
		bktRightBlock  = attrs.getDrawable(R.styleable.fs_bkt_right_block);
		multiplyBlock  = attrs.getDrawable(R.styleable.fs_multiply_block);
		divisionBlock  = attrs.getDrawable(R.styleable.fs_division_block);
	}

	public Drawables initDrawables(Context context){
		if (defaultBlock == null)
			defaultBlock = context.getResources().getDrawable(defIDDefaultBlock);
		if (movableBlock == null)
			movableBlock = context.getResources().getDrawable(defIDMovableBlock);
		if (changeableBlock == null)
			changeableBlock = context.getResources().getDrawable(defIDChangeableBlock);
		if (goodBlock == null)
			goodBlock = context.getResources().getDrawable(defIDGoodBlock);
		if (badBlock == null)
			badBlock = context.getResources().getDrawable(defIDBadBlock);
		if (unselectedBlock == null)
			unselectedBlock = context.getResources().getDrawable(defIDUnselectedBlock);
		if (checkBad == null)
			checkBad = context.getResources().getDrawable(defIDCheckBad);
		if (checkGood == null)
			checkGood = context.getResources().getDrawable(defIDCheckGood);

		if (plusBlock == null)
			plusBlock = context.getResources().getDrawable(defIDPlusBlock);
		if (minusBlock == null)
			minusBlock = context.getResources().getDrawable(defIDMinusBlock);
		if (equallyBlock == null)
			equallyBlock = context.getResources().getDrawable(defIDEquallyBlock);
		if (bktLeftBlock == null)
			bktLeftBlock = context.getResources().getDrawable(defIDBktLeftBlock);
		if (bktRightBlock == null)
			bktRightBlock = context.getResources().getDrawable(defIDBktRightBlock);
		if (multiplyBlock == null)
			multiplyBlock = context.getResources().getDrawable(defIDMultiplyBlock);
		if (divisionBlock == null)
			divisionBlock = context.getResources().getDrawable(defIDDivisionBlock);

		return this;
	}

	public Drawable getBadBlock() {
		return badBlock;
	}

	public void setBadBlock(Drawable badBlock) {
		this.badBlock = badBlock;
	}

	public Drawable getGoodBlock() {
		return goodBlock;
	}

	public void setGoodBlock(Drawable goodBlock) {
		this.goodBlock = goodBlock;
	}

	public Drawable getChangeableBlock() {
		return changeableBlock;
	}

	public void setChangeableBlock(Drawable changeableBlock) {
		this.changeableBlock = changeableBlock;
	}

	public Drawable getMovableBlock() {
		return movableBlock;
	}

	public void setMovableBlock(Drawable movableBlock) {
		this.movableBlock = movableBlock;
	}

	public Drawable getDefaultBlock() {
		return defaultBlock;
	}

	public void setDefaultBlock(Drawable defaultBlock) {
		this.defaultBlock = defaultBlock;
	}

	public Drawable getPlusBlock() {
		return plusBlock;
	}

	public void setPlusBlock(Drawable plusBlock) {
		this.plusBlock = plusBlock;
	}

	public Drawable getMinusBlock() {
		return minusBlock;
	}

	public void setMinusBlock(Drawable minusBlock) {
		this.minusBlock = minusBlock;
	}

	public Drawable getMultiplyBlock() {
		return multiplyBlock;
	}

	public void setMultiplyBlock(Drawable multiplyBlock) {
		this.multiplyBlock = multiplyBlock;
	}

	public Drawable getUnselectedBlock() {
		return unselectedBlock;
	}

	public void setUnselectedBlock(Drawable unselectedBlock) {
		this.unselectedBlock = unselectedBlock;
	}

	public Drawable getBktRightBlock() {
		return bktRightBlock;
	}

	public void setBktRightBlock(Drawable bktRightBlock) {
		this.bktRightBlock = bktRightBlock;
	}

	public Drawable getBktLeftBlock() {
		return bktLeftBlock;
	}

	public void setBktLeftBlock(Drawable bktLeftBlock) {
		this.bktLeftBlock = bktLeftBlock;
	}

	public Drawable getEquallyBlock() {
		return equallyBlock;
	}

	public void setEquallyBlock(Drawable equallyBlock) {
		this.equallyBlock = equallyBlock;
	}

	public Drawable getCheckBad() {
		return checkBad;
	}

	public void setCheckBad(Drawable checkBad) {
		this.checkBad = checkBad;
	}

	public Drawable getCheckGood() {
		return checkGood;
	}

	public void setCheckGood(Drawable checkGood) {
		this.checkGood = checkGood;
	}

	public Drawable getDivisionBlock() {
		return divisionBlock;
	}

	public void setDivisionBlock(Drawable divisionBlock) {
		this.divisionBlock = divisionBlock;
	}
}
