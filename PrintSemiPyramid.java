//This Code Prints stars in the below order
//*
//**
//***
public class PrintSemiPyramid {
    public static void main(String[] args) {
        int n = 3;
        for(int i=0; i<n; i++) {
            for(int j=i; j>=0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
