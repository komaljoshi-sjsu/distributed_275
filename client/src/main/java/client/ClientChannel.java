package client;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONObject;
import com.message.proto.QueryProcessorGrpc;
import com.message.proto.QueryRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;

import  com.message.resolver.MultiAddressNameResolverFactory;

public class ClientChannel {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		ClientChannel c = new ClientChannel();
		NameResolver.Factory nameResolverFactory = new MultiAddressNameResolverFactory(
	            new InetSocketAddress("localhost", 9090),
	            new InetSocketAddress("169.254.204.33", 9090)
//	            new InetSocketAddress("localhost", 8082)
	    );

	    ManagedChannel channel = ManagedChannelBuilder.forTarget("service")
	            .nameResolverFactory(nameResolverFactory)
	            .defaultLoadBalancingPolicy("round_robin")
	            .usePlaintext()
	            .build();
		c.makeBlockingRequest(channel);
		
	}
	
	@SuppressWarnings("unchecked")
	private void makeBlockingRequest(ManagedChannel channel) {
		String[] countries = {"ISLE","Summit","Austin","45.7","(88)","45.8","Pass","(40)","Mill","SR-44","WIDNR","Thedford","Rd.","CBRFC","Llano","Evanston","UTDEQ","MODOT","AG","ILLEPA","AK","AL","Gillette","AN","NENDR","Park","AQ","(Elizabethtown)","0","AR","1","AS","2","AT","3","AU","4","SNOTEL","5","Flora","AW","(41)","6","8","AZ","9","Tasley","I-95_@_Falling_Creek","East","BA","BB","BC","BD","B","BE","MODNR","BG","E","S78W","F","H","Kemmerer","Reservoir","M","N","BR","(42)","Q","BS","WAAQ","R","BARAGA","S","T","BY","W","BZ","Y","CA","Road)","CB","WNW","Center","(Cumberland)","CH","CI","CL","I-64_@_Rt_250_Afton_Mtn","CN","CO","(68)","Facilities","CR","(43)","Br","Quincy_Res","CT","CU","I-64_Bridge_@_Rt_30","BELLW","CZ","US-301","6.5","DC","DE","DK","DM","Jackson","DO","Cheyenne&Laramie","Rover","(44)","Frelsburg","EC","Wheatland","EE","SVWX","5NE","(46)","BROWCAQD","ES","54.8","(45)","State","End","Bridge","Dubois","Connector","NH-9","FI","FK","Blue_Mountain","FL","FM","MD-522","FR","(47)","61)","Aurora_Town_Hall","IDDEQ","Elbert","NiwotRidge","(22)","SARC","Merriman","118-IR70/IR71/SR315","GA","NWS-GLOS","Novice","Islands","NJDEP","Grasslands","I70","GL","I-66_@_Markham","GN","GR","GT","GU","(48)","AVALANCHE","(23)","FAWN","Manor","WSW","SR-10","SR190","HI","HK","HN","GMNP","Diamond_Hill","HR","FPWX","63)","HU","(49)","340.4","UPR","Laramie","SLO","IA","USARMY-COE","ID","IE","ColoState","TOOELE","TASKINAS","IL","Capability","IN","DOT","1.35","IS","3NE","IT","64)","Beddington","98.3","JP","Bend","65)","CAMPBELL","LXWS","KCESS","NREL","111.8","Argos","Anchorite","Bondurant","5SW","SNOWNET","AZMET","HNXWFO","USA","ODEQ","Marston_Lake_North","218)","KS","Samoa","KY","Tunnel","2.1","LA","Abutment","LB","VEGAS","DRI","Evansville","MADEP","I270","Beulah","LU","(10*)","PP_CogRR","MDE","Brady","MA","MB","MD","Guam","ME","Sundance","Rt_7_@_601_Purcelville","CEMP","SR-87","MH","MI","US52","MK","SR-82","MN","MO","MP","MS","US36","MT","MU","1NE","MX","20)","MY","GGWWFO","441","200","NA","201","NB","SR4","NC","ND","Canyon","NE","96.3","NF","1NW","NH","NI","209","NJ","NL","NM","Bastrop","Rt_15_@_Lucketts","NO","NS","NT","Buffalo&Gillette","NV","NW","21)","NY","NZ","LANL","113.7","Brownwood","217","OH","OK","(I-80)","ON","OR","SSE","CODWR","CAIC","SGXWFO","SSI","Point","IR77","YAKIMA","I-81_@_Cedar_Creek","AZDOT","PA","CPCRC","Garage","PE","226","SSW","0.5","PH","Leitchfield","Menard","0.8","PL","Ward_C-1","POINT","Pilot","Cody","Monacacy","PR","PT","PW","Rt_29_@_Crooked_Run","Rawlins","231","QC","LADEQ","Howe","Arlington","ATB/TRUM","Greely","Village","S65DWX","Kaycee","HILLSBGH","244","Altair","(US20)","248","RI","Jct","UPRR","RO","ENR308","Ridge","RU","1SE","Hill","25)","EPAOAP","Midfield","SC","SD","Choptank","SE","Rd","SG","1SW","SI","Ri","SK","ODOT","SW","Mullin","CHEBOYGAN","261","Rapids","TC","Appomattox","VCAPCD","TH","17.7","Sl","DELAWARE","TN","TR","TT","Vista","FGZWFO","TW","TX","MONT/GRE","ALERT","273","275","279","UK","FLDEP","UM","28.7","Drive","UTETRIBE","Midway","US","UT","MD-295","HPWREN","Seneca","AndrewsW","VA","VE","(40*)","Ave","NEDEQ","VI","SAFETYNET","I-95_@_MP_0.5","Cloverdale","105-IR70/IR75","VT","Buffalo&Sheridan","Shelf","WA","(Hawesville)","Grange","Site_B","Minidoka","Site_D","Ln.","Site_E","296","Site_G","Site_H","I-370","WI","Mountain","WRWX","(90)","WS","(I-235)","SMAQMD","WV","WY","Wood","CLIMAT","PDTWFO","Patuxent","RIDEM","MCSCN","S140W","ROTNWX","(91)","Jetty","PCMR","Savage","292.6","Saba","MEDEP","CARB","380.63","YT","(92)","Childress","ZA","BTAVAL","AROOSTOOK",".47","Mason","I-81_over_Rt_682","Creek","MD-646","USBR","Butte","53.85","Ulrichsville","(I-380)","Ave.","KNOX/Ashland","MSI","Road","CLARKDAQM","WADOECO","Dam","Verona","I-77_@_Exit_8","Facility","TCEQ","Goldthwaite","NJ-MESONET","WOMAN","CNRFC","PDXWFO","Sherburin-In-Elmet","1221.8","SR213","Rock","Channel","CAN","CAR","US-322","Flat","SFPU","CIMIS","9.9","INL","Encampment","USFS","SR249","98-SR161@18.1","DGLSHD","(50)","(74)","SR-252","Springs","Barge","Emigrant","Minerva","KSL","Squaw_Mountain","O'Neil","S61W","11.5","VTAPCD","(99)","Flats","(51)","Coleman","Bay","AGRIMET","Salem_VR","212.10","Ston","WADEQ","USIB","WSCA","Ranch","Syracuse","Rt_9_@_WV_Line","ME-CAR_Meso","Cove","Bowdoin","Rt_460_@_Candlers_Mtn","USGS","Salisbury_Park","Falls","92)","Line","Wayne","SUPERAWOS","79.2","14.76","(I-29)","RCPCEM","142.88","MIDNRE","(Slade)","Arco","137)","Gardnerville","US-421","SR-166","Hall","(3)","6NW","Stapleton","RES","SP/Briscoe","MetObs","INDEM","71)","(Henderson)","Shl","SR11","North","8SW","20.9","Buffalo","BLMWY","GOLDER","Area","Castell","31.0","GRC","(5)","ENE","Dunes","FORSYEAD","SR29","Fulton","Lander","MAMMOTH","Harbor","Well","MISC","(35)","(10)","(34)","IADNR","10SW","Peak","(6)","Henry","249.1","MENDOAQMD","Richfield","OKDEQ","(36)","Bardstown","53.2","(7)","Wilber","Meeteetsie","US-50","RIV","I-64_@_Rt_629","Clarksville","Riverton&S.Pass","(37)","(12)","LaPorte","Jonestown","OKWRB","Fredericksburg","(8)","Castle_Rock","Island","(38)","PGE","US395/US50","Columbus","(Fairfax)","WYDEQ","Rensselaer","SGGEWX","MATANZAS","Blvd.","HMMN","(39)","RIVER","134.5","I-64_over_Rivanna_River","ESE","CHEROKEE","MD-2","River","Hiwan_G.C.","Seymour","40.7","I-66_@_Haymarket","Rt_66_@_Rosslyn","ALAUSGS","SR57","Spring_Valley_Rd","2NE","SEWWFO","30)","DCEW","145.6","Jct.","(MVMS)","51.3","307","2NW","MFRWFO","Aberdeen","Smithville","Lake","TRIBALENV","WSCVAN","1360.4","SORD","(18)","(MMS)","City","Meadow_Rd_over_I-295","STA5WX","SBCAPCD","1.4","1.5","S75WX","JDWX","Br.","Schurz","Wall","bot",".5","Remote","County","331","I-89)","DUGWAY","Casper&Shoshoni","Brighton","West","Cal-Wood_Ranch","34)","MARICOPA","Lucas","GADNR","97.01","343","101","104","105","106","Dublin_Airport","107","108","Highlands_Ranch_WTP","109","MBAYUAPCD","TVA","2SE","Hamer","59)","SR540","Wymore","MD-32","CTDEP","112","114","Brighton_Ditch","117","10W","118","CFSW","119","10","11","JONES","12","15","16","18","19","Button_Rock","32.77","Taber","MAH/COL","1E","126","2)","128","18.6","129","LOXWFO","Bernard","Rt_220_@_Ashley_Plantation","Split","1N","20","ATB/LAKE","21","22","Dawson","23","24","1S","25","Sugarloaf","1W","Tomah_Rd","29","BCSI","Shores","Muldoon","(34/37)","131","I-295_@_Woodman","2E","KACHEMAK","379","Baker","12W","3)","I-695","139","NH-112","SCAN","Elgin","(8*)","2N","Beltline","30","32","2S","34","35","36","37","2W","38","NWAVAL","DEERVLY","140","EPAAQPS","141","142","Archbold","3E","145","146","Vincennes","Wharton","Bypass","4)","HAM/CLER","149","I-465","NH-101","3N","40","41","43","46","3W","NNE","49","Click","MD-180","Nikiski","Glenbrook","157","158","NNW","Strling","Hurley","CDPHE","52","53","Austi","384.5","4S","PSD","55","I-43","59","Casper","Winchell","SR739","161","162","NCDNRDAQC","I-480","ACRAWX","169","SHASAVAL","60","62","UNIVERSITY","Varina_Enon","Carson","Sweetwater","65","66","Fredricksburg","67","69","I-77","FHCHSX","172","(IA10)","174","CADWR","176","NPS","Plant","70","Junction","6S","74","75","MAH/POR","78","18)","I-65","LINNCOPH","FAA","180","181","S65CW","View","184","7E","188","Ranchester","80","81","Valera","Castle","NE-POWER-CO","83","84","86","SCDEHC","7W","(I-35)","89","Moon","194","195","196","198","9)","NORTHFIELD","199","I-271","Abbotsfrd","POR/SUM","49.7","90","Kingsland","Interchg,WV","91","325.4","93","94","96","WYSTO","98","I-89","Bullseye","Riv.","163)","ROOKER","Cheyenne","Turkey","Valley","Plaza","Rt_58_Bridge_@_Rt_265","I-90","MD-526","Frankfort","UAH","14.5","I-93","CALTRANS","Brewster","POIN","Sinclair","Lagrange","MAWN","MSOWFO","Complex","I-77_@_MP_54","Avenue","25.7","HIDEH","Monteview","Terreton","(85*)","140th","Yard","NH-16","56.06","SR151","36.6","7_@_287_Leesburg","MD-100","218.2","Birdseye","Interchange","NCOWCD","NHDES","(60)","US-219","NIFC","Dome","MD-331","Tipton","NWS","Roberts","12.2","Incline","Grove)","SAI","Sugarcreek","12.8","KENNECOTT","Aurora_Reservoir","Kokomo","Palamino","S331W","Louisville_Lake","BASIN","23.8","151.6","169)","Luckenbach","Alcova","Management","Kingsbury","(US30)","(87)","BAY","34.5","OHEPAOAPC"};
		int countryUpperBound = countries.length-1;
		System.out.println("Connected to server");
		
		//Scanner sc = new Scanner(System.in);
		//System.out.println("Enter lower bound of latitude:");
		
		
		long startTime = System.currentTimeMillis();
		QueryProcessorGrpc.QueryProcessorBlockingStub blockingStub = QueryProcessorGrpc.newBlockingStub(channel);
		for(int i = 1;i<=200;i++) {
			System.out.println("Client "+i+" Sending Request to server.....");
			Random rand = new Random(); //instance of random class
	        int upperbound = 100;
	        int intNum = rand.nextInt(upperbound);
			double lat = rand.nextDouble()*intNum;
			//System.out.println("Enter lower bound of longitude:");
			intNum = rand.nextInt(upperbound);
			double lon = rand.nextDouble()*intNum;
			//
			//System.out.println("Enter lower bound of elevation:");
			intNum = rand.nextInt(upperbound);
			double ele = rand.nextDouble()*intNum;
			
			int cntNum = rand.nextInt(countryUpperBound);
			String country = countries[cntNum];	
			
			JSONObject spatial = new JSONObject();
			spatial.put("country", country);
			spatial.put("lat", lat);
			spatial.put("lon", lon);
			spatial.put("elevation", ele);
			
			String query = spatial.toJSONString();
			String message = blockingStub.sendQuery(QueryRequest.newBuilder().setQuery(query).build()).getMessage();
			System.out.println("\n\nServer sent to request  "+i+":"+message);
		}
		
		long stopTime = System.currentTimeMillis();
		
		System.out.println(
				"total request processing time is " + ((stopTime - startTime) / 1000.0) + " seconds");
	}

}
