package model.bean;

public class Jogo {

    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private String plataforma;
    private int idVendedor;
    private String nomeArquivoImagem;
    private String dataLancamento;
    private String requisitosSistema;
    private String dataPublicacao;
    private int numeroDeVendas;
    private String dataUltimaVenda;
    private int totalDownloads;

    public Jogo() {}

    public void incrementDownloads() {
        this.totalDownloads++;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
    public int getIdVendedor() { return idVendedor; }
    public void setIdVendedor(int idVendedor) { this.idVendedor = idVendedor; }
    public String getNomeArquivoImagem() { return nomeArquivoImagem; }
    public void setNomeArquivoImagem(String nomeArquivoImagem) { this.nomeArquivoImagem = nomeArquivoImagem; }
    public String getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(String dataLancamento) { this.dataLancamento = dataLancamento; }
    public String getRequisitosSistema() { return requisitosSistema; }
    public void setRequisitosSistema(String requisitosSistema) { this.requisitosSistema = requisitosSistema; }
    public String getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(String dataPublicacao) { this.dataPublicacao = dataPublicacao; }
    public int getNumeroDeVendas() { return numeroDeVendas; }
    public void setNumeroDeVendas(int numeroDeVendas) { this.numeroDeVendas = numeroDeVendas; }
    public String getDataUltimaVenda() { return dataUltimaVenda; }
    public void setDataUltimaVenda(String dataUltimaVenda) { this.dataUltimaVenda = dataUltimaVenda; }
    public int getTotalDownloads() { return totalDownloads; }
    public void setTotalDownloads(int totalDownloads) { this.totalDownloads = totalDownloads; }
}