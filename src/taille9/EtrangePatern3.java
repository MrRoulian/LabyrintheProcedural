package taille9;

import patern.Patern;
import patern.Rotation;

public class EtrangePatern3 extends Patern {

	private static EtrangePatern3 instance0, instance90, instance180, instance270;

	private EtrangePatern3(Rotation r){
		super(r);

		structure[0]=true;
		structure[9]=false;
		structure[18]=false;
		structure[27]=false;
		structure[36]=false;
		structure[45]=false;
		structure[54]=false;
		structure[63]=false;
		structure[72]=false;
		structure[1]=true;
		structure[10]=true;
		structure[19]=false;
		structure[28]=true;
		structure[37]=true;
		structure[46]=true;
		structure[55]=false;
		structure[64]=true;
		structure[73]=true;
		structure[2]=false;
		structure[11]=false;
		structure[20]=false;
		structure[29]=false;
		structure[38]=false;
		structure[47]=true;
		structure[56]=false;
		structure[65]=false;
		structure[74]=false;
		structure[3]=true;
		structure[12]=true;
		structure[21]=true;
		structure[30]=true;
		structure[39]=false;
		structure[48]=true;
		structure[57]=true;
		structure[66]=true;
		structure[75]=false;
		structure[4]=false;
		structure[13]=false;
		structure[22]=false;
		structure[31]=false;
		structure[40]=false;
		structure[49]=true;
		structure[58]=false;
		structure[67]=false;
		structure[76]=false;
		structure[5]=true;
		structure[14]=true;
		structure[23]=true;
		structure[32]=true;
		structure[41]=true;
		structure[50]=true;
		structure[59]=false;
		structure[68]=true;
		structure[77]=true;
		structure[6]=true;
		structure[15]=true;
		structure[24]=false;
		structure[33]=false;
		structure[42]=false;
		structure[51]=true;
		structure[60]=false;
		structure[69]=false;
		structure[78]=false;
		structure[7]=false;
		structure[16]=false;
		structure[25]=false;
		structure[34]=true;
		structure[43]=false;
		structure[52]=true;
		structure[61]=true;
		structure[70]=true;
		structure[79]=false;
		structure[8]=true;
		structure[17]=true;
		structure[26]=true;
		structure[35]=true;
		structure[44]=false;
		structure[53]=false;
		structure[62]=false;
		structure[71]=false;
		structure[80]=false;

		applyRotation();
	}

	public static Patern getInstance(Rotation r) {
		switch(r){
		case r0:
			return instance0 == null?new EtrangePatern3(r):instance0;
		case r180:
			return instance180 == null?new EtrangePatern3(r):instance180;
		case r90:
			return instance90 == null?new EtrangePatern3(r):instance90;
		case r270:
			return instance270 == null?new EtrangePatern3(r):instance270;
		}
		return instance0;
	}

}
