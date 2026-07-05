public class SortAssignment {

    /**
     * クイックソートとマージソートの実装。
     * 引数、またはデフォルトのテストデータを用いてソートする
     * 
     * @param args コマンドライン引数（ソートしたい整数の並び　*任意）
     */
    public static void main(String[] args) {
        
        int[] originalData;

        // 引数argsがある場合、それをソート対象にする
        if (args.length > 0) {
            
            originalData = new int[args.length];
            
            try {
                for (int i = 0; i < args.length; i++) {
                    originalData[i] = Integer.parseInt(args[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("エラー: 引数には整数のみを指定してください。");
                return;
            }
        } else {
            // 引数がない場合、デフォルトのテストデータを使用する
            System.out.println("引数がないため、テストデータを使用します。");
            originalData = new int[]{5, 3, 8, 4, 9, 1, 6, 2, 7};
        }
        
        // クイックソート
        
        System.out.println("--- クイックソート ---");
        System.out.println("ソート前:");
        
        int[] data1 = originalData.clone();
        printArray(data1);
        
        // クイックソートを実行（配列、走査開始位置、走査終了位置を渡す）
        quickSort(data1, 0, data1.length - 1);
        
        System.out.println("ソート後:");
        printArray(data1);
        
        // マージソート
        
        int[] data2 = originalData.clone();
        
        System.out.println("\n--- マージソート ---");
        System.out.println("ソート前:");
        
        printArray(data2);
        
        // マージソートを実行（配列、走査開始位置、走査終了位置を渡す）
        mergeSort(data2, 0, data2.length - 1);
        
        System.out.println("ソート後:");
        printArray(data2);
    }
    
    /**
     * クイックソート(対象配列内の、指定した要素(left～right)の値を昇順にソートする)
     * 
     * @param arr 対象配列
     * @param left 走査開始位置のインデックス
     * @param right 走査終了位置のインデックス
     */
    public static void quickSort(int[] arr, int left, int right) {
        
        // 再帰終了条件（要素が1つ以下になったら終了）
        if (left >= right){
            return;
        }
        
        int pivot = arr[right];
        int i = left - 1;
        
        for (int j = left; j < right; j++) {
            
            // ピボットより小さい値を左に移動する
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        // ピボットの正しい位置
        int pivotIndex = i + 1;
        
        // ピボットの値を正しい位置に移動する
        swap(arr, pivotIndex, right);
        
        // ピボットの左側の値をクイックソートする
        quickSort(arr, left, pivotIndex - 1);
        
        // ピボットの右側の値をクイックソートする
        quickSort(arr, pivotIndex + 1, right);
    }
    
    /**
     * マージソート(対象配列内の、指定した要素(left～right)の値を昇順にソートする)
     * 
     * @param arr 対象配列
     * @param left 走査開始位置のインデックス
     * @param right 走査終了位置のインデックス
     */
    public static void mergeSort(int[] arr, int left, int right) {
        
        // 再帰終了条件（要素が1つ以下になったら終了）
        if (left >= right) {
            return;
        }
        
        // leftとrightの中間のインデックスを計算して変数midに入れる
        int mid = (left + right) / 2;
        
        // 左半分(left ～ mid)をマージソートする
        mergeSort(arr, left, mid);
        
        // 右半分(mid + 1 ～ right)をマージソートする
        mergeSort(arr, mid + 1, right);
        
        // 分解された配列を並べ替えながらマージする
        merge(arr, left, mid, right);
    }
    
    /**
     * 分解された配列を並べ替えながらマージするメソッド
     * 
     * @param arr 対象配列
     * @param left 走査開始位置のインデックス
     * @param mid 走査中間位置のインデックス
     * @param right 走査終了位置のインデックス
     */
    public static void merge(int[] arr, int left, int mid, int right) {
        
        // arrと同じ長さの一時配列を作成
        int[] temp = new int[arr.length];
        
        // arrを一時配列にコピー
        for (int m = left; m <= right; m++) {
            temp[m] = arr[m];
        }
        
        // ポインタの初期化
        // 左半分のスタート位置
        int i = left;
        // 右半分のスタート位置
        int j = mid + 1;
        // 書き出す位置
        int k = left;
        
        // 左と右を比較しながら元の配列arrに書き出す
        while (i <= mid && j <= right) {
            // 小さい方をarr[k]に書き出す
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
                k++;
            } else {
                arr[k] = temp[j];
                j++;
                k++;
            }
        }
        
        // 左半分に残りがあればすべて元の配列arrに戻す
        while (i <= mid) {
            // 残りをarr[k]に入れる
            arr[k] = temp[i];
            i++;
            k++;
        }
    }
    
    /**
     * 配列内の2つの値の位置を入れ替えるメソッド
     * 
     * @param arr 対象配列
     * @param i 入れ替える位置
     * @param j 入れ替える位置
     */
    public static void swap(int[] arr, int i, int j) {
        
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * 配列を表示するメソッド
     * 
     * @param arr 対象配列
     */
    public static void printArray(int[] arr) {
        
        for (int num : arr) {
            System.out.print(num + " ");
        }
        // 改行
        System.out.println();
    }
}