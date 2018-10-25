package taille9;

import patern.Patern;
import patern.Rotation;

public class EtrangePatern4 extends Patern {

	private static EtrangePatern4 instance0, instance90, instance180, instance270;

	private EtrangePatern4(Rotation r){
		super(r);

		structure[0]=false;
		structure[9]=false;
		structure[18]=true;
		structure[27]=true;
		structure[36]=true;
		structure[45]=true;
		structure[54]=true;
		structure[63]=true;
		structure[72]=true;
		structure[1]=false;
		structure[10]=false;
		structure[19]=false;
		structure[28]=true;
		structure[37]=false;
		structure[46]=false;
		structure[55]=false;
		structure[64]=false;
		structure[73]=true;
		structure[2]=true;
		structure[11]=false;
		structure[20]=false;
		structure[29]=false;
		structure[38]=true;
		structure[47]=false;
		structure[56]=false;
		structure[65]=false;
		structure[74]=true;
		structure[3]=true;
		structure[12]=true;
		structure[21]=false;
		structure[30]=false;
		structure[39]=false;
		structure[48]=true;
		structure[57]=false;
		structure[66]=false;
		structure[75]=true;
		structure[4]=true;
		structure[13]=false;
		structure[22]=true;
		structure[31]=false;
		structure[40]=false;
		structure[49]=false;
		structure[58]=true;
		structure[67]=false;
		structure[76]=true;
		structure[5]=true;
		structure[14]=false;
		structure[23]=false;
		structure[32]=true;
		structure[41]=false;
		structure[50]=false;
		structure[59]=false;
		structure[68]=true;
		structure[77]=true;
		structure[6]=true;
		structure[15]=false;
		structure[24]=false;
		structure[33]=false;
		structure[42]=true;
		structure[51]=false;
		structure[60]=false;
		structure[69]=false;
		structure[78]=true;
		structure[7]=true;
		structure[16]=false;
		structure[25]=false;
		structure[34]=false;
		structure[43]=false;
		structure[52]=true;
		structure[61]=false;
		structure[70]=false;
		structure[79]=false;
		structure[8]=true;
		structure[17]=true;
		structure[26]=true;
		structure[35]=true;
		structure[44]=true;
		structure[53]=true;
		structure[62]=true;
		structure[71]=false;
		structure[80]=false;

		applyRotation();
	}

	public static Patern getInstance(Rotation r) {
		switch(r){
		case r0:
			return instance0 == null?new EtrangePatern4(r):instance0;
		case r180:
			return instance180 == null?new EtrangePatern4(r):instance180;
		case r90:
			return instance90 == null?new EtrangePatern4(r):instance90;
		case r270:
			return instance270 == null?new EtrangePatern4(r):instance270;
		}
		return instance0;
	}

}
