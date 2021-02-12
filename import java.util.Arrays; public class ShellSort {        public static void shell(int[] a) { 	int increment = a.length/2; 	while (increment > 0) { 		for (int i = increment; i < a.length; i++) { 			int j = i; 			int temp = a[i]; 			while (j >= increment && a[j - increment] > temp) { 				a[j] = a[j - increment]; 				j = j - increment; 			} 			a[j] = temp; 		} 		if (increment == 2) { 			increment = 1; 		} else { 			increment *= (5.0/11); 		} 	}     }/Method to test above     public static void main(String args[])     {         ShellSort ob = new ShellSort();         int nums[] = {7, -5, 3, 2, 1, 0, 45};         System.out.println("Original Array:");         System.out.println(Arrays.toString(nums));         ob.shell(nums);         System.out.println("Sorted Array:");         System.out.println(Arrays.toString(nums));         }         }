import java.util.Arrays;
public class ShellSort {   
    public static void shell(int[] a) {
	int increment = a.length / 2;
	while (increment > 0) {
		for (int i = increment; i < a.length; i++) {
			int j = i;
			int temp = a[i];
			while (j >= increment && a[j - increment] > temp) {
				a[j] = a[j - increment];
				j = j - increment;
			}
			a[j] = temp;
		}
		if (increment == 2) {
			increment = 1;
		} else {
			increment *= (5.0 / 11);
		}
	}
    }

	// Method to test above
    public static void main(String args[])
    {
        ShellSort ob = new ShellSort();
        int nums[] = {7, -5, 3, 2, 1, 0, 45};
        System.out.println("Original Array:");
        System.out.println(Arrays.toString(nums));
        ob.shell(nums);
        System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(nums));
        }        
}
