package ar.edu.unlp.info.oo2.ejercicio08_DispositivoMovilYConexiones;

import java.util.zip.CRC32;

public class CRC32Calculator implements CRCCalculatorStrategy {

	@Override
	public int crcFor(String data) {
		// TODO Auto-generated method stub
		CRC32 crc = new CRC32();
		crc.update(data.getBytes());
		long result = crc.getValue();
		System.out.println((int)result);
		return (int) result;
	}

}
