package com.jforce.jsure.dashboard.manager.db.model;

import com.jforce.jsure.base.db.model.JsureDbEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@Comment("[EN]Multitenant Company Definitions [TR]Multitenant Şirket Tanımları")
@Audited
public class Company extends JsureDbEntity {


    private static final long serialVersionUID = 2614036485265711893L;

    @Comment("[EN]Company Code [TR]Şirket kodu")
    @Column(name = "company_code",unique = true,nullable = false,length = 8)
    private String companyCode;

    @Comment("[EN]Company Name [TR]Şirket adı")
    @Column(name = "company_name",nullable = false,length = 200)
    private String companyName;

}