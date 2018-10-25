package taille9;

import patern.Patern;
import patern.Rotation;

public class EtrangePatern2 extends Patern {

	private static EtrangePatern2 instance0, instance90, instance180, instance270;

	private EtrangePatern2(Rotation r){
		super(r);
		structure[0]=false;
		structure[9]=false;
		structure[18]=false;
		structure[27]=false;
		structure[36]=false;
		structure[45]=false;
		structure[54]=false;
		structure[63]=false;
		structure[72]=false;
		structure[1]=false;
		structure[10]=true;
		structure[19]=true;
		structure[28]=true;
		structure[37]=true;
		structure[46]=true;
		structure[55]=true;
		structure[64]=true;
		structure[73]=false;
		structure[2]=false;
		structure[11]=true;
		structure[20]=true;
		structure[29]=true;
		structure[38]=true;
		structure[47]=false;
		structure[56]=true;
		structure[65]=true;
		structure[74]=false;
		structure[3]=false;
		structure[12]=true;
		structure[21]=false;
		structure[30]=false;
		structure[39]=true;
		structure[48]=true;
		structure[57]=false;
		structure[66]=true;
		structure[75]=false;
		structure[4]=false;
		structure[13]=true;
		structure[22]=true;
		structure[31]=true;
		structure[40]=true;
		structure[49]=true;
		structure[58]=false;
		structure[67]=true;
		structure[76]=false;
		structure[5]=false;
		structure[14]=true;
		structure[23]=false;
		structure[32]=false;
		structure[41]=true;
		structure[50]=true;
		structure[59]=false;
		structure[68]=true;
		structure[77]=false;
		structure[6]=false;
		structure[15]=true;
		structure[24]=true;
		structure[33]=true;
		structure[42]=true;
		structure[51]=false;
		structure[60]=true;
		structure[69]=true;
		structure[78]=false;
		structure[7]=false;
		structure[16]=true;
		structure[25]=true;
		structure[34]=true;
		structure[43]=true;
		structure[52]=true;
		structure[61]=true;
		structure[70]=true;
		structure[79]=false;
		structure[8]=false;
		structure[17]=false;
		structure[26]=false;
		structure[35]=false;
		structure[44]=false;
		structure[53]=false;
		structure[62]=false;
		structure[71]=false;
		structure[80]=false;

		//applyRotation();
	}

	public static Patern getInstance(Rotation r) {
		switch(r){
		case r0:
			return instance0 == null?new EtrangePatern2(r):instance0;
		case r180:
			return instance180 == null?new EtrangePatern2(r):instance180;
		case r90:
			return instance90 == null?new EtrangePatern2(r):instance90;
		case r270:
			return instance270 == null?new EtrangePatern2(r):instance270;
		}
		return instance0;
	}

}
