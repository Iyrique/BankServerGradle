package crud;

public interface InterfaceCRUD {

    void createClient(String name, String birthday);

    void readClientById(int id);

    void readClientByNameAndBirthday(String name, String birthday);

    void readClientByName(String name);

    void readAllClients();

    void deleteClientByIdAndName(int id, String name);

    void deleteClientByNameAndBirthday(String name, String birthday);

    void updateClientById(int id);

    void updateBankByName(String name);

    void updateBankById(int id);

    void createCreditWithClientId(int id, String sum, String percent, String payment, String period);

    void createCreditWithClientNameAndBirthday(int name, String birthday, String sum, String percent, String payment, String period);

    void readCreditById(int id);

    void readCreditsByClientId(int id);

    void updateCreditById(int id);

    void deleteCreditById(int id);

    void createDepositWithClientId(int id, String sum, String percent, String period, Boolean topUp, boolean withdraw);

    void createDepositWithClientNameAndBirthday(String name, String birthday, String sum, String percent, String period, Boolean topUp, boolean withdraw);

    void readDepositById(int id);

    void readDepositsByClientId(int id);

    void updateDepositById(int id);

    void deleteDepositById(int id);


}
