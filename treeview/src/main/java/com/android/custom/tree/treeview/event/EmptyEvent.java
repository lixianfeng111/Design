package com.android.custom.tree.treeview.event;

public class EmptyEvent {

    private String content;

    public EmptyEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
