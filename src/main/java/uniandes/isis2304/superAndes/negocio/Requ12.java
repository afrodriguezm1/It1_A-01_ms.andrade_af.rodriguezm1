package uniandes.isis2304.superAndes.negocio;

import java.nio.file.ProviderMismatchException;

public class Requ12 implements VORequ12
{
	public Requ12(int week, String prodMejorVen, String prodPeorVen, String provMasSoli, String provMenosSoli,
			long idSucursal, String documentoCliente, long idVenta, String codigoBarras, long cantidadProdcuto) {
		super();
		this.week = week;
		this.prodMejorVen = prodMejorVen;
		this.prodPeorVen = prodPeorVen;
		this.provMasSoli = provMasSoli;
		this.provMenosSoli = provMenosSoli;
		this.idSucursal = idSucursal;
		this.documentoCliente = documentoCliente;
		this.idVenta = idVenta;
		this.codigoBarras = codigoBarras;
		this.cantidadProdcuto = cantidadProdcuto;
	}

	private int week;
	
	public String prodMejorVen;
	
	public String prodPeorVen;
	
	public String provMasSoli;
	
	public String provMenosSoli;
	
	public long idSucursal;
	
	public String documentoCliente;
	
	public long idVenta;
	
	public String codigoBarras;
	
	public long cantidadProdcuto;
	
	public Requ12()
	{
		this.week = 0;
		this.prodMejorVen ="";
		this.prodPeorVen ="";
		this.provMasSoli="";
		this.provMenosSoli="";
		this.idSucursal=0;
		this.documentoCliente="";
		this.idVenta=0;
		this.codigoBarras="";
		this.cantidadProdcuto=0;
	}

	@Override
	public int getWeek() 
	{
		// TODO Auto-generated method stub
		return this.week;
	}

	@Override
	public String getProdMejorVen() {
		// TODO Auto-generated method stub
		return this.prodMejorVen;
	}

	@Override
	public String getProdPeorVen() {
		// TODO Auto-generated method stub
		return this.prodPeorVen;
	}

	@Override
	public String getProvMasSoli() {
		// TODO Auto-generated method stub
		return this.provMasSoli;
	}

	@Override
	public String getProvMenosSoli() {
		// TODO Auto-generated method stub
		return this.provMenosSoli;
	}

	@Override
	public long getIdSucursal() {
		// TODO Auto-generated method stub
		return this.idSucursal;
	}

	@Override
	public String getDocumentoCliente() {
		// TODO Auto-generated method stub
		return this.documentoCliente;
	}

	@Override
	public long getIdVenta() {
		// TODO Auto-generated method stub
		return this.idVenta;
	}

	@Override
	public String getCodigoBarras() {
		// TODO Auto-generated method stub
		return this.codigoBarras;
	}

	@Override
	public long getCantidadProdcuto() {
		// TODO Auto-generated method stub
		return this.cantidadProdcuto;
	}

}
