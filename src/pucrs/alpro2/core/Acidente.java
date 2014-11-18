package pucrs.alpro2.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Acidente {

    private String tipoLogradouro;
    private String logradouro;
    private String tipoAcidente;
    private Date dataHora;
    private String diaDaSemana;
    private int feridos;
    private int mortes;
    private int mortesPosteriores;
    private int fatais;
    private int auto;
    private int taxi;
    private int lotacao;
    private int onibusUrbano;
    private int onibusInterurbano;
    private int caminhao;
    private int moto;
    private int carroca;
    private int bicicleta;
    private String tempo;
    private String noiteDia;
    private String regiao;

    public Acidente(String line) throws ParseException {

	String[] v = line.split(";");

	String[] nomeRuaCompleto = v[0].split(" ");

	tipoLogradouro = nomeRuaCompleto[0];

	StringBuilder builder = new StringBuilder();
	for (int i = 1; i < nomeRuaCompleto.length; i++) {
	    builder.append(nomeRuaCompleto[i]);
	    builder.append(" ");
	}

	logradouro = builder.toString();

	tipoAcidente = v[1];

	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMd HH:mm");
	String dateInString = v[2];

	Date date = formatter.parse(dateInString);
	dataHora = date;

	diaDaSemana = v[3];
	feridos = Integer.parseInt(v[4]);
	mortes = Integer.parseInt(v[5]);
	mortesPosteriores = Integer.parseInt(v[6]);
	fatais = Integer.parseInt(v[7]);
	auto = Integer.parseInt(v[8]);
	taxi = Integer.parseInt(v[9]);
	lotacao = Integer.parseInt(v[10]);
	onibusUrbano = Integer.parseInt(v[11]);
	onibusInterurbano = Integer.parseInt(v[12]);
	caminhao = Integer.parseInt(v[13]);
	moto = Integer.parseInt(v[14]);
	carroca = Integer.parseInt(v[15]);
	bicicleta = Integer.parseInt(v[16]);
	tempo = v[17];
	noiteDia = v[18];
	regiao = v[19];

    }

    public String getTipoLogradouro() {
	return tipoLogradouro;
    }

    public String getLogradouro() {
	return logradouro;
    }

    public String getTipoAcidente() {
	return tipoAcidente;
    }

    public Date getDataHora() {
	return dataHora;
    }

    public String getDiaDaSemana() {
	return diaDaSemana;
    }

    public int getFeridos() {
	return feridos;
    }

    public int getMortes() {
	return mortes;
    }

    public int getMortesPosteriores() {
	return mortesPosteriores;
    }

    public int getFatais() {
	return fatais;
    }

    public int getAuto() {
	return auto;
    }

    public int getTaxi() {
	return taxi;
    }

    public int getLotacao() {
	return lotacao;
    }

    public int getOnibusUrbano() {
	return onibusUrbano;
    }

    public int getOnibusInterurbano() {
	return onibusInterurbano;
    }

    public int getCaminhao() {
	return caminhao;
    }

    public int getMoto() {
	return moto;
    }

    public int getCarroca() {
	return carroca;
    }

    public int getBicicleta() {
	return bicicleta;
    }

    public String getTempo() {
	return tempo;
    }

    public String getNoiteDia() {
	return noiteDia;
    }

    public String getRegiao() {
	return regiao;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();

	builder.append(Acidente.class.getName());
	builder.append(" Logradouro = ");
	builder.append(logradouro);
	builder.append(" Data e Hora = ");
	builder.append(dataHora);
	builder.append(" Tipo Acidente = ");
	builder.append(tipoAcidente);
	builder.append(" Dias da Semana = ");
	builder.append(diaDaSemana);
	builder.append(" Feriados = ");
	builder.append(feridos);
	builder.append(" Mortes = ");
	builder.append(mortes);
	builder.append(" Mortes Posteriores = ");
	builder.append(mortesPosteriores);
	builder.append(" Fatais = ");
	builder.append(fatais);
	builder.append(" Auto = ");
	builder.append(auto);
	builder.append(" Taxi = ");
	builder.append(taxi);
	builder.append(" Lotacao = ");
	builder.append(lotacao);
	builder.append(" Onibus Urbano = ");
	builder.append(onibusUrbano);
	builder.append(" Onibus Interurbano = ");
	builder.append(onibusInterurbano);
	builder.append(" Caminhao = ");
	builder.append(caminhao);
	builder.append(" Moto = ");
	builder.append(moto);
	builder.append(" Carroca = ");
	builder.append(carroca);
	builder.append(" Bicicleta = ");
	builder.append(bicicleta);
	builder.append(" Tempo = ");
	builder.append(tempo);
	builder.append(" Noite e Dia = ");
	builder.append(noiteDia);
	builder.append(" Regiao = ");
	builder.append(regiao);
	builder.append("");

	return builder.toString();
    }

}
