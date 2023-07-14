%% Data
programmingLnguage.language = ["C","C++","C#","Java","Python"];
programmingLnguage.info2021 = [14.32,7.14,4.91,11.23,11.03];
interestRate = [-2.40,+0.36,+0.16,-5.49,+1.72];
programmingLnguage.info2020 = programmingLnguage.info2021 - interestRate;

%% Sort
[~,order] = sort(programmingLnguage.info2021,'descend');
programmingLnguage.language = programmingLnguage.language(order);
programmingLnguage.info2021 = programmingLnguage.info2021(order);
programmingLnguage.info2020 = programmingLnguage.info2020(order);

info = [programmingLnguage.info2020;programmingLnguage.info2021];
label = ["2020","2021"];

%% Color
Colors = [89,171,227;
          31,71,136]./255;
% Colors = [244,208,63;
%           54,37,17]./255;
newColors = nan(5,3);
for i=1:size(newColors,2)
    newColors(:,i) = linspace(Colors(1,i),Colors(2,i),5);
end

%% Plots
h = bar(info);
for i=1:size(newColors,1)
    set(h(i),'facecolor',newColors(i,:))
end
set(gca,'xticklabel',label);
legend(programmingLnguage.language);
legend('location','northeastoutside');