function flag = isAllowedtoRecombine(RecombinationRate)
    randomNumber = rand();
    if randomNumber <= RecombinationRate
        flag = 1;
    else
        flag = 0;
    end
end