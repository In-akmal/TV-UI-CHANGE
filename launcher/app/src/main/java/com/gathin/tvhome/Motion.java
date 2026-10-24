package com.gathin.tvhome;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

/** Motion tokens and animation helpers. */
final class Motion {

    /** Canonical focus duration in milliseconds. */
    static final int FOCUS_MS = 180;
    /** Canonical unfocus duration in milliseconds. */
    static final int UNFOCUS_MS = 150;
    /** Canonical focus scale factor. */
    static final float FOCUS_SCALE = 1.05f;
    /** Canonical focus vertical lift in dp. */
    static final float FOCUS_TRANSLATE_Y_DP = -4f;
    /** Subtle decelerate interpolator (fast response, zero bounce/overshoot). */
    static final Interpolator DECELERATE = new DecelerateInterpolator(1.2f);

    /** The longest any reduced-motion animation runs, in milliseconds. */
    static final int REDUCED_MS = 90;
    /** Critically damped (no bounce) and stiff enough that any move, even across the screen, settles in 5 frames. */
    static final float REDUCED_STIFFNESS = 15000f;
    /** The spring stops once it is within this many pixels of its target. */
    static final float REDUCED_MIN_CHANGE = 2f;

    private Motion() {
    }

    static void reduce(SpringAnimation spring) {
        spring.setSpring(new SpringForce().setStiffness(REDUCED_STIFFNESS).setDampingRatio(SpringForce.DAMPING_RATIO_NO_BOUNCY));
        spring.setMinimumVisibleChange(REDUCED_MIN_CHANGE);
    }
}
