package com.example.organizer.model.enums;

import java.util.Objects;

public enum SettingSwitch {
    EVERYDAY("Каждый день"),
    EVERYWEEK("Каждую неделю");
    private final String setting;
    SettingSwitch(String setting){
        this.setting = setting;
    }

    public static SettingSwitch getInstance(String setting){
      if(Objects.equals(setting, "Каждый день"))  return SettingSwitch.EVERYDAY;
      if(Objects.equals(setting, "Каждую неделю"))  return SettingSwitch.EVERYWEEK;
      return null;
    }

    public String getSetting() {
        return setting;
    }
}
