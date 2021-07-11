package com.novo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novo.dto.ComprobantePagoDto;
import com.novo.modelo.Cliente;
import com.novo.modelo.ComprobantePago;
import com.novo.modelo.MetodoPago;
import com.novo.modelo.Producto;
import com.novo.modeloDao.ComprobantePagoDao;

@Service
public class ComprobantePagoService {

	@Autowired
    private ComprobantePagoDao comprobantePagoDao;
	
	public List<ComprobantePagoDto> listComprobante() {
        List<ComprobantePago> comprobantes = comprobantePagoDao.findAll();
        List<ComprobantePagoDto> comprobanteDtos = new ArrayList<>();
        for(ComprobantePago comprobante : comprobantes) {
        	ComprobantePagoDto comprobantePagoDto = getDtoFromComprobante(comprobante);
        	comprobanteDtos.add(comprobantePagoDto);
        }
        return comprobanteDtos;
    }
	
	public static ComprobantePagoDto getDtoFromComprobante(ComprobantePago comprobante) {
		ComprobantePagoDto comprobantePagoDto = new ComprobantePagoDto(comprobante);
        return comprobantePagoDto;
    }
	
	public static ComprobantePago getComprobanteFromDto(ComprobantePagoDto comprobantePagoDto,Cliente cliente,
			Producto producto, MetodoPago metodoPago) {
		ComprobantePago comprobantePago=new ComprobantePago(comprobantePagoDto,cliente,producto,metodoPago);
		return comprobantePago;
	}
	
	public void addComprobantePago(ComprobantePagoDto comprobantePagoDto,Cliente cliente,
			Producto producto, MetodoPago metodoPago) {
		ComprobantePago comprobantePago=getComprobanteFromDto(comprobantePagoDto,cliente,producto,metodoPago);
		comprobantePagoDao.save(comprobantePago);
		
	}
	public void updateComprobantePago(long id,ComprobantePagoDto comprobantePagoDto,Cliente cliente,
			Producto producto, MetodoPago metodoPago) {
		ComprobantePago comprobantePago=getComprobanteFromDto(comprobantePagoDto,cliente,producto,metodoPago);
		comprobantePago.setId(id);
		comprobantePagoDao.save(comprobantePago);
	}
	
	
	
	
	
	
	
}
