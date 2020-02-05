package nl.richrail.persistance;

import nl.richrail.domain.singleton.Company;

public interface CompanyDao {
    public void save(Company company);
    public Company load();
}
