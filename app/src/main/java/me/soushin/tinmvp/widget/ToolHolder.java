package me.soushin.tinmvp.widget;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.soushin.tinmvp.R;

/**
 * 标题栏
 * @auther SouShin
 * @time 2019/1/24 09:11
 */
public class ToolHolder {
    @BindView(R.id.img_back)
    public ImageView imgBack;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.toolbar)
    public ConstraintLayout toolbar;
    public Unbinder unbinder;

    public ToolHolder(View view) {
        unbinder=ButterKnife.bind(this, view);
    }
}
