package ar.edu.unlp.info.oo2.ejercicio08_DispositivoMovilYConexiones;

public class CRC16Calculator implements CRCCalculatorStrategy {

	@Override
	public int crcFor(String data) {
		int crc = 0xFFFF;
		for (int j = 0; j < data.getBytes().length; j++) {
			crc = ((crc >>> 8) | (crc << 8)) & 0xffff;
			crc ^= (data.getBytes()[j] & 0xff);
			crc ^= ((crc & 0xff) >> 4);
			crc ^= (crc << 12) & 0xffff;
			crc ^= ((crc & 0xFF) << 5) & 0xffff;
		}
		crc &= 0xffff;
		System.out.print(crc);
		return crc;
		}
	
}
