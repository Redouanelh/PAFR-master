package nl.richrail.persistance;

import nl.richrail.domain.singleton.Company;

public interface CompanySerializer {
    String serialize(Company company);
    Company deserialize(String data);
}
