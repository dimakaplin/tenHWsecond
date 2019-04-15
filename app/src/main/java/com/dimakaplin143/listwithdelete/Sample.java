package com.dimakaplin143.listwithdelete;

public class Sample {
    private int imgId;
    private String name;
    private String target;
    private String type;
    private boolean checked = false;

    public String getType() {
        return type;
    }

    public Sample(String name, String target, String type) {
        this.type = type;
        switch(type) {
            case "tech":
                this.imgId = R.drawable.ic_settings_black_24dp;
                break;
            case "trash":
                this.imgId = R.drawable.ic_delete_black_24dp;
                break;
            case "eat":
                this.imgId = R.drawable.ic_local_dining_black_24dp;
                break;
            default:
                this.imgId = R.drawable.ic_help_outline_black_24dp;
        }

        this.name = name;
        this.target = target;
    }

    public int getImgId() {
        return imgId;
    }

    public String getName() {
        return name;
    }

    public String getTarget() {
        return target;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
