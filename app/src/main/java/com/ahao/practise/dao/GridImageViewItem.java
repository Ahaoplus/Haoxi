package com.ahao.practise.dao;

public class GridImageViewItem {
    private static final String LARGE_BASE_URL = "https://hbimg.huabanimg.com/";
    private static final String THUMB_BASE_URL = "https://hbimg.huabanimg.com/";
    public static GridImageViewItem[] ITEMS = new GridImageViewItem[] {
            new GridImageViewItem("Flying in the Light", "Romain Guy", "e88b792c3c924a6a0a5f5b081d8603e453b2d4f9d6528-KyaMti_fw658/format/webp"),
            new GridImageViewItem("Caterpillar", "Romain Guy", "05ebde586dd67a8b0e81dd1116fa7338f2c842561713e-FCAgXT_fw658/format/webp"),
            new GridImageViewItem("Look Me in the Eye", "Romain Guy", "fc4b9282d6d5cf050afe57f8e2e862553400fc7571617-3c0Dzd_fw658/format/webp"),
            new GridImageViewItem("Flamingo", "Romain Guy", "8e211f60e459518a4165b3dfdc204f2822d121c8153dbe-hcCdUL_fw658/format/webp"),
            new GridImageViewItem("Rainbow", "Romain Guy", "f4509f16a70ecf6e37380bd1bb36d786c82e34d612b049-AII2k3_fw658/format/webp"),
            new GridImageViewItem("Over there", "Romain Guy", "8d1af312024ec4f370f70b7ec325e66f683a95ac2e383-ULg8rB_fw658/format/webp"),
            new GridImageViewItem("Jelly Fish 2", "Romain Guy", "49d9327b88295c6bf2b385584fe76e03c8ae6d3776a4c-xsqZmn_fw658/format/webp"),
            new GridImageViewItem("Lone Pine Sunset", "Romain Guy", "e3732412b459d3dfad6f81c2247299f7801c6be6461bbe-tMstjt_fw658/format/webp"),
    };

    public static GridImageViewItem getItem(int id) {
        for (GridImageViewItem item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private final String mName;
    private final String mAuthor;
    private final String mFileName;

    GridImageViewItem (String name, String author, String fileName) {
        mName = name;
        mAuthor = author;
        mFileName = fileName;
    }

    public int getId() {
        return mName.hashCode() + mFileName.hashCode();
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getName() {
        return mName;
    }

    public String getPhotoUrl() {
        return LARGE_BASE_URL + mFileName;
    }

    public String getThumbnailUrl() {
        return THUMB_BASE_URL + mFileName;
    }
}
