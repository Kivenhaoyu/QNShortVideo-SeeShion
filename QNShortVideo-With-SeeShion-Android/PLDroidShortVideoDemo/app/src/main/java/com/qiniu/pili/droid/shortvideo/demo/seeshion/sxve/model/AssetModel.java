package com.qiniu.pili.droid.shortvideo.demo.seeshion.sxve.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.qiniu.pili.droid.shortvideo.demo.seeshion.sxve.AssetDelegate;
import com.qiniu.pili.droid.shortvideo.demo.seeshion.sxve.util.Size;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AssetModel {
    public static final int TYPE_MEDIA = 1;
    public static final int TYPE_TEXT = 2;

    public final Size size;
    public final int type;

    public final AssetUi ui;
    private final Bitmap mBitmap;

    public AssetModel(String folder, JSONObject asset, AssetDelegate delegate) throws JSONException {
        String name = folder + "/assets/" + asset.getString("name");
        mBitmap = BitmapFactory.decodeFile(name);

        JSONArray sizeArray = asset.getJSONArray("size");
        size = new Size(sizeArray.getInt(0), sizeArray.getInt(1));

        JSONObject ui = asset.getJSONObject("ui");
        type = ui.getInt("type");
        if (type == TYPE_TEXT) {
            this.ui = new TextUiModel(folder, asset, mBitmap, delegate, size);
        } else {
            this.ui = new MediaUiModel(folder, ui, mBitmap, delegate, size);
        }
    }
}
