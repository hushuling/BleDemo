package com.xiekang.bluetooths.databinding;
import com.xiekang.bluetooths.R;
import com.xiekang.bluetooths.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityBluetoothConectBindingImpl extends ActivityBluetoothConectBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.line1, 1);
        sViewsWithIds.put(R.id.tv_stuat, 2);
        sViewsWithIds.put(R.id.btn_stop, 3);
        sViewsWithIds.put(R.id.btn_restart, 4);
        sViewsWithIds.put(R.id.tv_result, 5);
        sViewsWithIds.put(R.id.btn_qingniu, 6);
        sViewsWithIds.put(R.id.btn_xueya, 7);
        sViewsWithIds.put(R.id.btn_Kruikang_oxgen, 8);
        sViewsWithIds.put(R.id.btn_Kangtai_oxgen, 9);
        sViewsWithIds.put(R.id.DrawThreadNW, 10);
        sViewsWithIds.put(R.id.btn_aikang, 11);
        sViewsWithIds.put(R.id.btn_Sugar_Oxygen, 12);
        sViewsWithIds.put(R.id.btn_Kadik, 13);
        sViewsWithIds.put(R.id.btn_lepu, 14);
        sViewsWithIds.put(R.id.btn_tida, 15);
        sViewsWithIds.put(R.id.btn_aiaoletemp, 16);
        sViewsWithIds.put(R.id.btn_aike_sugar, 17);
        sViewsWithIds.put(R.id.btn_aiaole_sugar, 18);
        sViewsWithIds.put(R.id.btn_kangtai_uri, 19);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityBluetoothConectBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }
    private ActivityBluetoothConectBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.xiekang.bluetooths.bluetooths.oxgen.draw.DrawThreadNW) bindings[10]
            , (android.widget.Button) bindings[18]
            , (android.widget.Button) bindings[16]
            , (android.widget.Button) bindings[11]
            , (android.widget.Button) bindings[17]
            , (android.widget.Button) bindings[13]
            , (android.widget.Button) bindings[9]
            , (android.widget.Button) bindings[19]
            , (android.widget.Button) bindings[8]
            , (android.widget.Button) bindings[14]
            , (android.widget.Button) bindings[6]
            , (android.widget.Button) bindings[4]
            , (android.widget.Button) bindings[3]
            , (android.widget.Button) bindings[12]
            , (android.widget.Button) bindings[15]
            , (android.widget.Button) bindings[7]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[2]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}