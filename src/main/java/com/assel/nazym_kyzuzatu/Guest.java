package com.assel.nazym_kyzuzatu;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/**
 * Модель данных для гостя
 */
public class Guest {

    @JsonProperty("guestId")
    private int guestId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("guestCount")
    private int guestCount;

    @JsonProperty("spouse")
    private String spouse;  // "Иә" или "Жоқ"

    @JsonProperty("attendance")
    private String attendance;  // "yes", "no", "pending"

    @JsonProperty("submittedAt")
    private LocalDateTime submittedAt;

    @JsonProperty("notes")
    private String notes;

    // Конструкторы
    public Guest() {
        this.guestCount = 1;
        this.spouse = "Жоқ";
        this.attendance = "pending";
    }

    public Guest(String name, String attendance) {
        this.name = name;
        this.attendance = attendance;
        this.guestCount = 1;
        this.spouse = "Жоқ";
    }

    public Guest(int guestId, String name, int guestCount, String spouse, String attendance) {
        this.guestId = guestId;
        this.name = name;
        this.guestCount = guestCount;
        this.spouse = spouse;
        this.attendance = attendance;
    }

    // Getters and Setters
    public int getGuestId() { return guestId; }
    public void setGuestId(int guestId) { this.guestId = guestId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getGuestCount() { return guestCount; }
    public void setGuestCount(int guestCount) { this.guestCount = guestCount; }

    public String getSpouse() { return spouse; }
    public void setSpouse(String spouse) { this.spouse = spouse; }

    public String getAttendance() { return attendance; }
    public void setAttendance(String attendance) { this.attendance = attendance; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", name='" + name + '\'' +
                ", guestCount=" + guestCount +
                ", spouse='" + spouse + '\'' +
                ", attendance='" + attendance + '\'' +
                ", submittedAt=" + submittedAt +
                '}';
    }
}

