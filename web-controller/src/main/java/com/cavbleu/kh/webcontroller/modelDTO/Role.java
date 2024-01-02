package com.cavbleu.kh.webcontroller.modelDTO;

/**
 * @author cavbleu
 * @project cloud-sensor
 * @create 2024-01-01 09:23
 */
public enum Role {
    USER ("user"),
    ADMIN ("admin");

    private String title;

    Role(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
