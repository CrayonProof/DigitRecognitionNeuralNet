package package_01;

public class Layer {

	public Layer(int neur, String actiFunc) {
		final int neurons[] = new int[neur];
		final String Function = actiFunc;
	}

	public double activate(String type, double x, double yk, double xk,
			double ys, double xs) {
		if (type.equals("Sigmoid")) {
			return 1 * yk / 1 + Math.pow(Math.E, x * xk + xs) + ys;
		} else
			return 1.0;
	}
}
