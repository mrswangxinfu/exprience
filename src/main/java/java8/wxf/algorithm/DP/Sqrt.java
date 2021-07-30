package java8.wxf.algorithm.DP;

import java.util.HashSet;
import java.util.LinkedList;

public class Sqrt {
    public static double mySqrt(double x) {
        double left=0,right=x,mid=0,res=0;
        if (x==1||x==0) {
            return x;
        }
        while (true) {
            double l=left;
            double r=right;
            mid=left +(right-left)/2;
            if (mid*mid==x) {
                return mid;
            }
            if ((mid*mid)>x) {
                right = mid;
            } else {
                res = mid;
                left = mid;
            }
            if (l==left&&r==right||(right-left)<1.0e-6) {
                break;
            }
        }
        return res;
    }
    public static int mySqrt(int x) {
        int left=0,right=x,mid,res=0;
//        int limit = 1;
//        limit<<=16;
        if (x<=1) {
            return x;
        }
        while (true) {
            mid=left +((right-left)>>1);
            // 处理数据越界
//            if ((mid*mid)>x||mid*mid<0||mid>=limit) {
//                right = mid;
//            }
            // 使用除法不用乘法 达到避免数据越界
            if (mid>x/mid) {
                right = mid;
            }
            else {
                left = mid;
            }
            if (mid<=x/mid&&(mid+1)>x/(mid+1)) {
                return mid;
            }
        }
    }

    public static int mySqrt2(int x) {
        if (x <= 1) return x;
        int start = 1, end = x / 2;
        while (start < end) {
            int mid = ((end - start) >> 1) + start;
            if (mid <= x / mid && (mid + 1) > x / (mid + 1))
                return mid;
            else if (mid < x / mid) start = mid + 1;
            else end = mid;
        }
        return start;
    }
    public static void main(String[] args) {
       double res = mySqrt(1023);
        System.out.println(res);
    }
}
