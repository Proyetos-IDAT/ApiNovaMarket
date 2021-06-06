package com.novo.interfaces;

import java.util.List;
import java.util.Map;

import com.novo.modelo.Proveedor;

public interface ProveedorInterface {
	public List<Map<String,Object>>listarpro();
	public int agregarpro(Proveedor pr);
	public int editarpro(Proveedor pr);
	public int eliminarpro(int id);
}
