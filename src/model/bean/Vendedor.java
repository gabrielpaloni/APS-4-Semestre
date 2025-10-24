package model.bean;

public class Vendedor {

    private int id;
    private String nomeLoja; // Ex: Nome da loja ou nome do vendedor
    private String email;
    private String senha;

    // Construtor vazio
    public Vendedor() {}

    // Construtor para cadastro
    public Vendedor(String nomeLoja, String email, String senha) {
        this.nomeLoja = nomeLoja;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
