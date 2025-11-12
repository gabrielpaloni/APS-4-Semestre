package model.bean;

import java.sql.Timestamp;

public class Compra {

    private int id;
    private int idComprador;
    private int idJogo;
    private Timestamp dataCompra;
    private double precoPago;

    public Compra() {}

    public Compra(int idComprador, int idJogo, double precoPago) {
        this.idComprador = idComprador;
        this.idJogo = idJogo;
        this.precoPago = precoPago;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdComprador() { return idComprador; }
    public void setIdComprador(int idComprador) { this.idComprador = idComprador; }
    public int getIdJogo() { return idJogo; }
    public void setIdJogo(int idJogo) { this.idJogo = idJogo; }
    public Timestamp getDataCompra() { return dataCompra; }
    public void setDataCompra(Timestamp dataCompra) { this.dataCompra = dataCompra; }
    public double getPrecoPago() { return precoPago; }
    public void setPrecoPago(double precoPago) { this.precoPago = precoPago; }
}