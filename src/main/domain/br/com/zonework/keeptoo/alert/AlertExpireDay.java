package br.com.zonework.keeptoo.alert;

import br.com.zonework.keeptoo.base.abstracsClasse.AbstractEntity;

import java.time.LocalDate;

public class AlertExpireDay extends AbstractEntity {
    private LocalDate expire;
    private String description;

    public AlertExpireDay() {  }

    public AlertExpireDay(LocalDate expire, String description) {
        this.expire = expire;
        this.description = description;
    }

    public LocalDate getExpire() {
        return expire;
    }

    public void setExpire(LocalDate expire) {
        this.expire = expire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
