package com.kusu.constructor.Settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.kusu.constructor.R;

/**
 * Created by mikhail.chvarkou on 1/4/2017.
 */

public class Drawables {

	// TODO: 1/4/2017 знаки операций нужны

	private static @DrawableRes int defIDDefaultBlock = R.drawable.bg_default;
	private static @DrawableRes int defIDMovableBlock = R.drawable.bg_movable;
	private static @DrawableRes int defIDChangeableBlock = R.drawable.bg_changeable;
	private static @DrawableRes int defIDGoodBlock = R.drawable.bg_good;
	private static @DrawableRes int defIDBadBlock = R.drawable.bg_bad;

	private Drawable defaultBlock;
	private Drawable movableBlock;
	private Drawable changeableBlock;
	private Drawable goodBlock;
	private Drawable badBlock;

	public Drawables(TypedArray attrs) {
		if (attrs == null)
			return;
		defaultBlock = attrs.getDrawable(R.styleable.fs_default_block);
		badBlock = attrs.getDrawable(R.styleable.fs_bad_block);
		goodBlock = attrs.getDrawable(R.styleable.fs_good_block);
		movableBlock = attrs.getDrawable(R.styleable.fs_movable_block);
		changeableBlock = attrs.getDrawable(R.styleable.fs_changeable_block);
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
}
