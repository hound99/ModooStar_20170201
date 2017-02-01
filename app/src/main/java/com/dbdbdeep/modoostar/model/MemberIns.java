package com.dbdbdeep.modoostar.model;

import com.dbdbdeep.modoostar.helper.BaseModel;

public class MemberIns extends BaseModel {
    public String m_nickname;
    public String m_sex;
    public String m_app_uid;

    @Override
    public String toString() {
        return "MemberIns{" +
                "m_nickname='" + m_nickname + '\'' +
                ", m_sex='" + m_sex + '\'' +
                ", m_app_uid='" + m_app_uid + '\'' +
                "} " + super.toString();
    }
}
