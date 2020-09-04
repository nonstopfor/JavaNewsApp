package com.java.zhangzhexin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public interface BaseView {

   void start(Intent intent);
   Context getMyContext();
}
