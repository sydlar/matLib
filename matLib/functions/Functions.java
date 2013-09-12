package matLib.functions;

public class Functions {


	public static void main(String[] args){
		Function f = Trig.sin;
		Function g = f.add(Trig.cos).multiply(.5*Math.sqrt(2));

		System.out.println(g.evaluate(.25*Math.PI));

		Polynomial p = new Polynomial(new double[]{1.0,1.0});
		p = p.multiply(-1.0);
		Polynomial q = p.multiply(-1.0);
		Polynomial r = p.add(q);
		System.out.println(r.evaluate(1.0));
		System.out.println(r);
	}
}
