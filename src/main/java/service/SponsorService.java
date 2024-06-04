package service;

import java.sql.Connection;

public interface SponsorService {
    public void addSponsor(Connection con);
    public void getSponsorByName(Connection con);
    public void updateSponsorByName(Connection con);
    public void deleteSponsorByName(Connection con);
}
