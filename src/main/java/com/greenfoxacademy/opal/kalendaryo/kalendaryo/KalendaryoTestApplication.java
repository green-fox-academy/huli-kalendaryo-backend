package com.greenfoxacademy.opal.kalendaryo.kalendaryo;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.CalendarId;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.MergedCalendar;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.entity.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.testEnvironment.TestAuthAndUserService;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.testEnvironment.TestCalendarIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KalendaryoTestApplication implements CommandLineRunner {

    @Autowired
    TestAuthAndUserService authAndUserService;

    @Autowired
    TestCalendarIdService calendarIdService;

    public static void main(String[] args) {
        SpringApplication.run(KalendaryoTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserModel userModel1 = new UserModel("clienttoken1", "haldirster@gamil.com");
        UserModel userModel2 = new UserModel("clienttoken2", "test@gustr.com");

        AuthModel authModel1 = new AuthModel("haldirster@gamil.com",
                "4/AACRDU1aDj_giBnvAgQs9sGT1vwPksTeZ2XP8G0f60hi3-DynzjXjFDjIVQ_WAHikJcFS6_L3Luv6gDzJ2njxac",
                "Balazs Salfay", userModel1,
                "ya29.GlxyBXIgfIdwt2YzAUd2cd4F3Wn-vDylZhTMwkOnKncG8LxSbEAyOt_q5_PdskgNCa0tx-B9zeJ5Mm4ibH4py4YabSB3uMtqsVI3n-u1C0OdZXEOXWaP1-370RmxiQ");
        AuthModel authModel2 = new AuthModel("balazs.salfay@gmail.com",
                "4/AAAACKZwRRju9hs0fdmU5WaXzJFkFfikz2I6MgkCb1P9R5t_ppFTJFG-hkR7dh_7-FY8hbS1-Uyc6v9kh1jO8zs",
                "Balazs Salfay", userModel1,
                "ya29.GlxyBTE6xGxra7mTRkI_Xz45UVN3Xe54sT09BESnM1sZczsqjERpSE7o2TZ0gHRD3tDiGfEBXME7Gi3vfxuVe2zNhoyvCv68AowXi8UHFwcgL1cvLrmT7tpLL9slwg");
        authAndUserService.saveAuthModel(authModel1);
        authAndUserService.saveAuthModel(authModel2);

        MergedCalendar mergedCalendar1 = new MergedCalendar(userModel1, "haldirster@gamil.com", "outputcalid1");
        MergedCalendar mergedCalendar2 = new MergedCalendar(userModel2, "test@gustr.com", "outputcalid2");

        CalendarId calendarId1 = new CalendarId("id1", authModel1, mergedCalendar1);
        CalendarId calendarId2 = new CalendarId("id2", authModel1, mergedCalendar2);
        CalendarId calendarId3 = new CalendarId("id3", authModel2, mergedCalendar1);
        CalendarId calendarId4 = new CalendarId("id4", authModel2, mergedCalendar2);
        calendarIdService.saveCalendarId(calendarId1);
        calendarIdService.saveCalendarId(calendarId2);
        calendarIdService.saveCalendarId(calendarId3);
        calendarIdService.saveCalendarId(calendarId4);
    }
}
