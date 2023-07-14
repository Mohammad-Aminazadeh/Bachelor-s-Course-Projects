function offspring =  completeOffsprings(randomNumber,individualSize,offspring,parent)
    counter = randomNumber + 1;
    for j=1:individualSize
        if (offspring ~= parent(j))
            offspring(counter) = parent(j);
            counter = counter + 1;
        end
    end
end