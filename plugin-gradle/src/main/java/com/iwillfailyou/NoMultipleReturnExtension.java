package com.iwillfailyou;

import com.iwillfailyou.inspection.sources.java.JavaSourceMask;
import com.iwillfailyou.inspections.nomultiplereturn.NoMultipleReturn;
import com.iwillfailyou.inspections.staticfree.Staticfree;
import com.iwillfailyou.plugin.Inspection;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("allfinal") // only for gradle plugin framework
public class NoMultipleReturnExtension {
    private final List<Boolean> disabled;
    private final List<Integer> threshold;

    public NoMultipleReturnExtension() {
        this(
            Arrays.asList(false),
            Arrays.asList(0)
        );
    }

    public NoMultipleReturnExtension(
        final List<Boolean> disabled,
        final List<Integer> threshold
    ) {
        this.disabled = disabled;
        this.threshold = threshold;
    }

    public void setDisabled(final boolean disabled) {
        this.disabled.set(0, disabled);
    }

    public void setThreshold(final int threshold) {
        this.threshold.set(0, threshold);
    }

    public Inspection inspection() {
        final Inspection inspection;
        if (disabled.get(0)) {
            inspection = new Inspection.Fake();
        } else {
            inspection = new NoMultipleReturn(
                new JavaSourceMask(),
                threshold.get(0)
            );
        }
        return inspection;
    }
}