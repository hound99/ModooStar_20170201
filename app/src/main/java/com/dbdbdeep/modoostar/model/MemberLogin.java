package com.dbdbdeep.modoostar.model;

import com.dbdbdeep.modoostar.helper.BaseModel;

/**
 * Created by leekh on 2017. 1. 11..
 */

public class MemberLogin extends BaseModel {
    public String fg_exists;
    public String m_nickname;
    public String m_sex;
    public String m_app_uid;

    @Override
    public String toString() {
        return "MemberLogin{" +
                "fg_exists='" + fg_exists + '\'' +
                ", m_nickname='" + m_nickname + '\'' +
                ", m_sex='" + m_sex + '\'' +
                ", m_app_uid='" + m_app_uid + '\'' +
                "} " + super.toString();
    }
}
