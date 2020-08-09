package com.iwillfailyou.inspections;

import com.iwillfailyou.inspections.staticfree.Staticfree;
import com.iwillfailyou.plugin.Inspection;

import java.util.List;

@SuppressWarnings("allfinal") // only for gradle plugin framework
public class StaticfreeExtension implements InspectionExtension {
    private final CommonExtension common;

    public StaticfreeExtension() {
        this(
            new CommonExtension()
        );
    }

    public StaticfreeExtension(
        final CommonExtension common
    ) {
        this.common = common;
    }

    @Override
    public void setDisabled(final boolean disabled) {
        this.common.setDisabled(disabled);
    }

    @Override
    public void setExclude(final List<String> exclude) {
        this.common.setExclude(exclude);
    }

    @Override
    public void inheritExclude(final List<String> exclude) {
        this.common.inheritExclude(exclude);
    }

    @Override
    public void setThreshold(final int threshold) {
        this.common.setThreshold(threshold);
    }

    @Override
    public Inspection inspection() {
        final Inspection inspection;
        if (common.getDisabled()) {
            inspection = new Inspection.Fake();
        } else {
            inspection = new Staticfree(
                common.mask(),
                common.getThreshold()
            );
        }
        return inspection;
    }
}
