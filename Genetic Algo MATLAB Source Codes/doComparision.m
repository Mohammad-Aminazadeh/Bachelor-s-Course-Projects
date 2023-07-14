%% Data
language = ["C","C++","C#","Java","Python"];
info2021 = [14.32,7.14,4.91,11.23,11.03];
interestRate = [-2.40,+0.36,+0.16,-5.49,+1.72];
info2020 = info2021 - interestRate;
info = [info2020;info2021];
label = ["2020","2021"];
Colors = [207,58,36;
          246,36,89;
          191,85,236;
          25,181,254;
          38,194,129]./255;

%% Plots
h = bar(info);
for i=1:size(Colors,1)
    set(h(i),'facecolor',Colors(i,:))
end
set(gca,'xticklabel',label);
legend(language);