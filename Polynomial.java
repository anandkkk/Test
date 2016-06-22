package edu.biu.scapi.primitives.dlog;
import edu.biu.scapi.primitives.dlog.CryptoPpDlogGF;

public class Polynomial extends CryptoPpDlogGF{

/*

x[], y[] are points arrays
n : # of points
x_position : x coordinate at which we want to find y

 */
	int x[],y[],w[],v[];
	int n,x_position;
	//CryptoPpDlogGF ring = new CryptoPpDlogGF();
	private native void PrepareBulkPolynomialInterpolation(long ring,int w[], int x[], int n);
	private native void PrepareBulkPolynomialInterpolationAt(long ring, int v[], int x_position, int x[], int w[], int n);
	private native int BulkPolynomialInterpolateAt(long ring,int y[], int v[], int n);
	

	static {
		System.loadLibrary("CryptoPPJavaInterface");
	}



	public Polynomial(){
		super();
	}

	public int doInterpolation(int X[],int Y[], int N, int X_Position)
	{
		
		
		n=N;
		x_position = X_Position;
		x=new int[n];		
		x=X;
		y=new int[n];		
		y=Y;
		w=new int[n];		
		v=new int[n];		

		PrepareBulkPolynomialInterpolation(pointerToField,w,x,n);
		PrepareBulkPolynomialInterpolationAt(pointerToField,v,x_position,x,w,n);
		return BulkPolynomialInterpolateAt(pointerToField,y,v,n);
	}

}
