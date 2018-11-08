package me.vponomarenko.feature.b;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import me.vponomarenko.core.SimpleTextWatcher;
import me.vponomarenko.core.TextHolder;
import me.vponomarenko.feature.b.di.FeatureBComponent;
import me.vponomarenko.feature.b.di.TextHolderForFeatureB;
import me.vponomarenko.injectionmanager.IHasComponent;
import me.vponomarenko.injectionmanager.x.XInjectionManager;

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

public class FragmentB extends Fragment implements IHasComponent<FeatureBComponent> {

    @Inject
    TextHolder singletonTextHolder;

    @Inject
    @TextHolderForFeatureB
    TextHolder featureBTextHolder;

    private SimpleTextWatcher editTextWatcherForSingleton = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(@NotNull Editable s) {
            if (s.length() == 0) return;
            singletonTextHolder.setText(s.toString());
        }
    };

    private SimpleTextWatcher editTextWatcherForFeatureB = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(@NotNull Editable s) {
            if (s.length() == 0) return;
            featureBTextHolder.setText(s.toString());
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feature_b, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FeatureBComponent component = XInjectionManager.bindComponent(this);
        component.inject(this);
        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.childFragmentContainer, new FragmentChildB())
                    .commit();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText singleton = view.findViewById(R.id.editText_singleton);
        EditText featureB = view.findViewById(R.id.editText_feature_b);
        singleton.addTextChangedListener(editTextWatcherForSingleton);
        featureB.addTextChangedListener(editTextWatcherForFeatureB);
    }

    @NotNull
    @Override
    public FeatureBComponent getComponent() {
        return FeatureBComponent.Initializer.Companion.init();
    }

    @NotNull
    @Override
    public String getComponentKey() {
        return getClass().toString();
    }
}
