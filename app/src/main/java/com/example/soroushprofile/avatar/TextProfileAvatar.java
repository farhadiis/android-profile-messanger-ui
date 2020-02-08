package com.example.soroushprofile.avatar;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.soroushprofile.R;
import com.example.soroushprofile.models.User;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class TextProfileAvatar extends ProfileAvatar {


    TextProfileAvatar(@NonNull Activity activity, @NonNull User user,
                      @NonNull ImageView mHeaderImageView,
                      @NonNull AvatarPaletteDelegate delegate) {
        super(activity, user, mHeaderImageView, delegate);
    }

    @Override
    public void drawAvatar(ImageView imageView) {
        if (!getUser().getName().isEmpty()) {
            String text = getUser().getName().substring(0, 1);
            int colorPrimaryDark = ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark);
            Resources resources = getActivity().getResources();
            int size = (int) resources.getDimension(R.dimen.avatar_size);
            Bitmap bitmap = generateTextThumbnail(text, colorPrimaryDark, size);
            imageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap generateTextThumbnail(String text, int color, int size) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(size);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);

        int padding = size / 2;
        int trueWidth = width;
        if (width > height) height = width;
        else width = height;
        Bitmap image = Bitmap.createBitmap(width + padding, height + padding, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawColor(color);
        canvas.drawText(text, (width / 2 - trueWidth / 2) + padding / 2, baseline + padding / 2f, paint);
        return image;
    }

}
