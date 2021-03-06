package Interviews.RobinHood;

public class Q11_SawToothSequence {
	private int getSawToothCount(int[] arr){
		int count = 0;
		for(int i = 0; i < arr.length-1; i++) {
			boolean up = false;  //current streak status is both up and down are false in start
			boolean down = false;
			for(int j = i+1; j < arr.length; j++) {
				if(arr[j-1] == arr[j]) continue;
				else if(!up && !down){   //start of the up or down streak
					if(arr[j-1] < arr[j]) up = !up;
					else if(arr[j-1] > arr[j]) down = !down;
					count++;
				}else if(up){  //up found already and now looking for down
					if(arr[j-1] > arr[j]){
						down = !down;
						up = !up;
						count++;
					}else break;
				}else if(down){  //down found already and now looking for up
					if(arr[j-1] < arr[j]){
						up = !up;
						down = !down;
						count++;
					}else break;
				}
			}
		}
		return count;
	}
	public static void main(String[] args) {
		int[] arr = {1,2,1,2,1};
		Q11_SawToothSequence cl = new Q11_SawToothSequence();
		System.out.println(cl.getSawToothCount(arr));
	}
}
