package com.assel.nazym_kyzuzatu;

/**
 * Класс для хранения статистики гостей
 */
public class GuestStatistics {
    private int total;
    private int confirmed;
    private int declined;
    private int pending;
    private int totalGuests;

    public GuestStatistics(int total, int confirmed, int declined, int pending, int totalGuests) {
        this.total = total;
        this.confirmed = confirmed;
        this.declined = declined;
        this.pending = pending;
        this.totalGuests = totalGuests;
    }

    // Getters
    public int getTotal() { return total; }
    public int getConfirmed() { return confirmed; }
    public int getDeclined() { return declined; }
    public int getPending() { return pending; }
    public int getTotalGuests() { return totalGuests; }

    @Override
    public String toString() {
        return "GuestStatistics{" +
                "total=" + total +
                ", confirmed=" + confirmed +
                ", declined=" + declined +
                ", pending=" + pending +
                ", totalGuests=" + totalGuests +
                '}';
    }
}

