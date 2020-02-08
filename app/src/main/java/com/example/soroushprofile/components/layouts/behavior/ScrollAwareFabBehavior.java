package com.example.soroushprofile.components.layouts.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ScrollAwareFabBehavior extends CoordinatorLayout.Behavior<LinearLayout> {

    private boolean isShown = true;

    public ScrollAwareFabBehavior(Context context, AttributeSet attrs) {
        super();
    }

    public ScrollAwareFabBehavior() {
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull LinearLayout child, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull LinearLayout child, @NonNull View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed,
                               int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed, type, consumed);
        if (dyUnconsumed > 0 && isShown) {
            isShown = false;
            for (int i = 0; i < child.getChildCount(); i++) {
                FloatingActionButton fab = (FloatingActionButton) child.getChildAt(i);
                fab.hide();
            }
        } else if (dyUnconsumed < 0 && !isShown) {
            isShown = true;
            for (int i = 0; i < child.getChildCount(); i++) {
                FloatingActionButton fab = (FloatingActionButton) child.getChildAt(i);
                fab.show();
            }
        }
    }
}
