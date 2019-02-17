package com.ethmeff.factorybackend;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.UUID;

import com.ethmeff.factorybackend.model.Part;

public class DemoData {

	// zf
	public static Part Break_Disc = new Part("Break Disc", new BigInteger("7"),
			UUID.fromString("253043b6-9ce1-404e-954b-2e00fd55d1d8").toString(), false, Arrays.asList(""));
	public static Part Break_Pipes = new Part("Break Pipes", new BigInteger("3"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));
	public static Part Break_Pads = new Part("Break Pads", new BigInteger("23"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));

	// dai
	public static Part brake = new Part("Brake", new BigInteger("4"),
			UUID.fromString("28d40ddd-6afe-4ee8-a995-ee24793a27c2").toString(), false,
			Arrays.asList(Break_Disc.getPartId(), Break_Pipes.getPartId(), Break_Pads.getPartId()));
	public static Part airbag = new Part("Airbags", new BigInteger("20234"),
			UUID.fromString("0b9127bd-a92c-4652-b605-ec1dc2f80364").toString(), false, Arrays.asList(""));
	public static Part engine = new Part("Engine", new BigInteger("101"),
			UUID.fromString("82d8f5eb-b870-4b23-93c0-70154949da06").toString(), false, Arrays.asList(""));
	public static Part headlight = new Part("Headlights", new BigInteger("1"),
			UUID.fromString("e023bf56-8af7-4d92-9ff9-0598f6ac3417").toString(), false, Arrays.asList(""));
	public static Part car = new Part("Car", new BigInteger("1337"),
			UUID.fromString("f2f789a8-62ef-4191-8398-9bf096ff5917").toString(), false,
			Arrays.asList(brake.getPartId(), airbag.getPartId(), engine.getPartId(), headlight.getPartId()));

	// for another car, but by daimler
	public static Part Headlights2 = new Part("Headlight", new BigInteger("733"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));
	public static Part Brake2 = new Part("Brake", new BigInteger("785"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));
	public static Part Engine2 = new Part("Engine", new BigInteger("3457"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));
	public static Part Airbags2 = new Part("Airbags", new BigInteger("68"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));
	public static Part Headlights3 = new Part("Headlight", new BigInteger("861"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));
	public static Part Brake3 = new Part("Brake", new BigInteger("995"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));
	public static Part Engine3 = new Part("Engine", new BigInteger("567"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));
	public static Part Airbags3 = new Part("Airbags", new BigInteger("987"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));
	public static Part Headlight4 = new Part("Headlight", new BigInteger("94"), UUID.randomUUID().toString(), false,
			Arrays.asList(""));
}
