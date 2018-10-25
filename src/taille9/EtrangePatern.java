package taille9;

import patern.Patern;
import patern.Rotation;

public class EtrangePatern extends Patern {

	private static EtrangePatern instance0, instance90, instance180, instance270;

	private EtrangePatern(Rotation r){
		super(r);

		structure[0]=true;
		structure[9]=false;
		structure[18]=true;
		structure[27]=true;
		structure[36]=true;
		structure[45]=true;
		structure[54]=true;
		structure[63]=false;
		structure[72]=true;
		structure[1]=true;
		structure[10]=false;
		structure[19]=true;
		structure[28]=true;
		structure[37]=false;
		structure[46]=false;
		structure[55]=false;
		structure[64]=false;
		structure[73]=true;
		structure[2]=true;
		structure[11]=false;
		structure[20]=true;
		structure[29]=true;
		structure[38]=false;
		structure[47]=true;
		structure[56]=true;
		structure[65]=false;
		structure[74]=true;
		structure[3]=true;
		structure[12]=false;
		structure[21]=false;
		structure[30]=false;
		structure[39]=false;
		structure[48]=true;
		structure[57]=false;
		structure[66]=false;
		structure[75]=true;
		structure[4]=true;
		structure[13]=true;
		structure[22]=true;
		structure[31]=true;
		structure[40]=true;
		structure[49]=true;
		structure[58]=false;
		structure[67]=false;
		structure[76]=false;
		structure[5]=true;
		structure[14]=false;
		structure[23]=false;
		structure[32]=false;
		structure[41]=false;
		structure[50]=true;
		structure[59]=false;
		structure[68]=false;
		structure[77]=true;
		structure[6]=true;
		structure[15]=false;
		structure[24]=true;
		structure[33]=true;
		structure[42]=false;
		structure[51]=true;
		structure[60]=true;
		structure[69]=false;
		structure[78]=true;
		structure[7]=true;
		structure[16]=false;
		structure[25]=true;
		structure[34]=true;
		structure[43]=false;
		structure[52]=false;
		structure[61]=false;
		structure[70]=false;
		structure[79]=true;
		structure[8]=true;
		structure[17]=false;
		structure[26]=true;
		structure[35]=true;
		structure[44]=true;
		structure[53]=true;
		structure[62]=true;
		structure[71]=false;
		structure[80]=true;

		applyRotation();
	}

	public static Patern getInstance(Rotation r) {
		switch(r){
		case r0:
			return instance0 == null?new EtrangePatern(r):instance0;
		case r180:
			return instance180 == null?new EtrangePatern(r):instance180;
		case r90:
			return instance90 == null?new EtrangePatern(r):instance90;
		case r270:
			return instance270 == null?new EtrangePatern(r):instance270;
		}
		return instance0;
	}

}
